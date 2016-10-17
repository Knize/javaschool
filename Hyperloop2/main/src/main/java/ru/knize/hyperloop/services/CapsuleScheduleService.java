package ru.knize.hyperloop.services;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.knize.hyperloop.DTO.CapsuleScheduleDTO;
import ru.knize.hyperloop.entities.CapsuleEntity;
import ru.knize.hyperloop.entities.CapsulesScheduleEntity;
import ru.knize.hyperloop.entities.EdgeEntity;
import ru.knize.hyperloop.entities.StationEntity;
import ru.knize.hyperloop.repositories.CapsuleScheduleRepository;
import ru.knize.hyperloop.services.pathfinder.Graph;
import ru.knize.hyperloop.services.pathfinder.PathFinderService;
import ru.knize.hyperloop.util.CapsuleTravelingMath;
import ru.knize.hyperloop.util.TimeUtil;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by knize on 01.10.16.
 */
@Service
public class CapsuleScheduleService {

    @Autowired
    CapsuleScheduleRepository capsuleScheduleRepository;
    @Autowired
    CapsuleService capsuleService;
    @Autowired
    StationService stationService;
    @Autowired
    EdgeService edgeService;
    @Autowired
    TrafficService trafficService;

    @Transactional
    public List<CapsulesScheduleEntity> getByDepTime(Timestamp time, StationEntity fromStation) {
        return capsuleScheduleRepository.findByDepTime(time, fromStation);
    }

    @Transactional
    public boolean hasFreeSeats(int requested, CapsulesScheduleEntity entity) {
        int used = trafficService.getTraffic(entity.getTripID(), entity.getFromStation());
        return entity.getCapsule().getSeatsNumber() - used - requested >= 0;
    }

    @Transactional
    public List<CapsulesScheduleEntity>
    findByNextStationAndDepTime(Timestamp time,
                                StationEntity fromStation,
                                StationEntity toStation) {
        return capsuleScheduleRepository.findByNextStationAndDepTime(time, fromStation, toStation);
    }

    @Transactional
    public List<CapsulesScheduleEntity> findByNextStationsAndTimeBorders(Timestamp fromTime,
                                                                         Timestamp toTime,
                                                                         StationEntity fromStation,
                                                                         StationEntity toStation) {
        return capsuleScheduleRepository.findByNextStationAndTimeBorders(fromTime, toTime, fromStation, toStation);
    }

    @Transactional
    public CapsulesScheduleEntity getScheduleById(int scheduleId) {
        return capsuleScheduleRepository.findById(scheduleId);
    }

    @Transactional
    public List<CapsulesScheduleEntity> getScheduleList() {
        return Lists.newArrayList(capsuleScheduleRepository.findAll());
    }

    @Transactional
    public CapsulesScheduleEntity addSchedule(CapsulesScheduleEntity cse) {
        cse = capsuleScheduleRepository.save(cse);
        capsuleScheduleRepository.flush();
        return cse;
    }

    @Transactional
    public List<CapsulesScheduleEntity> getByTripId(long tripId) {
        return capsuleScheduleRepository.findByTripId(tripId);
    }

    @Transactional
    public List<CapsulesScheduleEntity> getByStationsId(int fromStationId, int toStationId) {
        return capsuleScheduleRepository.findByStationsId(fromStationId, toStationId);
    }

    @Transactional
    public List<CapsulesScheduleEntity> getByFromStationId(int fromStationId) {
        return capsuleScheduleRepository.findByFromStationId(fromStationId);
    }

    @Transactional
    public List<CapsulesScheduleEntity> getByToStationId(int toStationId) {
        return capsuleScheduleRepository.findByToStationId(toStationId);
    }

    @Transactional
    public List<CapsulesScheduleEntity> getByCapsuleId(int capsuleId) {
        return capsuleScheduleRepository.findByCapsuleId(capsuleId);
    }


    public CapsulesScheduleEntity createCapsuleScheduleEntity(CapsuleScheduleDTO csDTO) {
        CapsulesScheduleEntity cse = new CapsulesScheduleEntity();
        try {
            int capsuleId = csDTO.getCapsuleId();
            int stationId = csDTO.getFromStationId();
            CapsuleEntity capsule = capsuleService.getCapsuleById(capsuleId);
            StationEntity station = stationService.getStationById(stationId);

            String timestampStr = csDTO.getDepartureTime();
            SimpleDateFormat timestampFormat = new SimpleDateFormat("dd MM, yyyy hh:mm");

            Date timestampDate = timestampFormat.parse(timestampStr);

            long time = timestampDate.getTime();
            Timestamp timestampArrival = new Timestamp(time);
            Timestamp timestampDeparture = new Timestamp(timestampArrival.getTime() + +300 * 1000L);

            cse.setTripType(1);
            cse.setCapsule(capsule);
            cse.setFromStation(station);
            cse.setArrivalTime(timestampArrival);
            cse.setDepartureTime(timestampDeparture);
            java.util.Date date = new java.util.Date();
            long tripUID = date.getTime();
            cse.setTripID(tripUID);
        } catch (ParseException | NumberFormatException e) {

        }

        return cse;

    }

    public CapsulesScheduleEntity csDTOtoEntity(CapsuleScheduleDTO csDTO) {
        CapsulesScheduleEntity cse = new CapsulesScheduleEntity();

        cse.setId(csDTO.getId());
        cse.setDepartureTime(TimeUtil.parseTimestamp(csDTO.getDepartureTime()));
        cse.setArrivalTime(TimeUtil.parseTimestamp(csDTO.getArrivalTime()));
        cse.setCapsule(capsuleService.getCapsuleById(csDTO.getCapsuleId()));

        cse.setEdge(edgeService.getEdgeById(csDTO.getEdgeId()));
        cse.setFromStation(stationService.getStationById(csDTO.getFromStationId()));
        cse.setTripID(csDTO.getTripId());
        cse.setTripType(csDTO.getTripType());
        return cse;
    }

    @Transactional
    public void fillSchedule(CapsuleScheduleDTO capsuleScheduleDTO) {
        Date date = new Date();
        long tripUID = date.getTime();

        StationEntity startStation = stationService.getStationById(capsuleScheduleDTO.getFromStationId());

        Timestamp startDepartureTime = TimeUtil.parseTimestamp(capsuleScheduleDTO.getDepartureTime());

        CapsuleEntity capsule = capsuleService.getCapsuleById(capsuleScheduleDTO.getCapsuleId());

        EdgeEntity edge = edgeService.edgeByStation(startStation);
        int branchId = edge.getBranch().getId();
        List<EdgeEntity> edgesOfBranch = edgeService.getBranch(branchId);
        List<StationEntity> endStations = stationService.getEndStationsForBranch(branchId);
        StationEntity endStation;
        if (endStations.get(0).equals(startStation)) {
            endStation = endStations.get(1);
        } else {
            endStation = endStations.get(0);
        }
        Set<Integer> stationsIdOfBranch = new HashSet<>();
        for (EdgeEntity e : edgesOfBranch) {
            stationsIdOfBranch.add(e.getFromStation().getId());
            stationsIdOfBranch.add(e.getToStation().getId());
        }
        List<StationEntity> stationsOfBranch = stationsIdOfBranch.stream()
                .map(stationId -> stationService.getStationById(stationId))
                .collect(Collectors.toList());
        Graph graph = new Graph(stationsOfBranch, edgesOfBranch);
        PathFinderService pathFinderService = new PathFinderService(graph);
        pathFinderService.execute(startStation); // start
        LinkedList<StationEntity> path = pathFinderService.getPath(endStation); // end

        Timestamp departureTime = startDepartureTime;
        for (int i = 0; i < path.size() - 1; i++) {
            StationEntity fromStation = path.get(i);
            StationEntity toStation = path.get(i + 1);
            EdgeEntity currentEdge = edgeService
                    .getEdgeByStationsId(fromStation.getId(), toStation.getId());

            Duration tripTime = CapsuleTravelingMath.computeTime(currentEdge.getRangeKm());

            CapsulesScheduleEntity cse = new CapsulesScheduleEntity();
            cse.setCapsule(capsule);
            cse.setTripID(tripUID);
            cse.setFromStation(fromStation);
            cse.setToStation(toStation);
            cse.setEdge(currentEdge);
            cse.setDepartureTime(departureTime);
            cse.setArrivalTime(new Timestamp(departureTime.getTime() + tripTime.toMillis()));
            departureTime = new Timestamp(cse.getArrivalTime().getTime() + 300 * 1000L);
            capsuleScheduleRepository.save(cse);
        }

    }
}



