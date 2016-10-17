package ru.knize.hyperloop.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import ru.knize.hyperloop.entities.*;
import ru.knize.hyperloop.services.*;
import ru.knize.hyperloop.services.pathfinder.Graph;
import ru.knize.hyperloop.services.pathfinder.PathFinderService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by knize on 14.09.16.
 */

/**
 * This class dedicated to computing, getting and saving
 * information about number of passengers in capsule by stages of the trip.
 */
@Service
public class TrafficUtil {
    @Autowired
    CapsuleScheduleService capsuleScheduleService;
    @Autowired
    EdgeService edgeService;
    @Autowired
    StationService stationService;
    @Autowired
    TrafficService trafficService;


    /**
     * @param from departure station
     * @param to   arrival station
     * @param cse  First schedule entry of the trip
     * @return true if there is free places in capsule
     */
    public boolean canGo(StationEntity from, StationEntity to, CapsulesScheduleEntity cse) {
        boolean can = true;

        List<StationEntity> path = getPath(from, to);

        int stationIndex = 0;
        while (stationIndex < path.size() - 1) {
            int fromStationId = path.get(stationIndex).getId();
            int toStationId = path.get(stationIndex + 1).getId();
            EdgeEntity currrentEdge = edgeService.getEdgeByStationsId(fromStationId, toStationId);

        }

        return can;
    }

    private List<CapsulesScheduleEntity> searchTripContinueVariants(int requested, List<StationEntity> path, CapsulesScheduleEntity cse) {

        if (path.size() == 1) {
            List<CapsulesScheduleEntity> result = new ArrayList<>();
            return result;
        }

        StationEntity currentStation = path.get(0);
        StationEntity nextStation = path.get(1);

        List<CapsulesScheduleEntity> continueVariants = capsuleScheduleService
                .findByNextStationAndDepTime(cse.getArrivalTime(), currentStation, nextStation)
                .stream().filter(__ -> capsuleScheduleService.hasFreeSeats(requested, __))
                .collect(Collectors.toList());
        Optional<CapsulesScheduleEntity> cseC = continueVariants.stream()
                .filter(__ -> __.getCapsule().equals(cse.getCapsule()))
                .findFirst();
        CapsulesScheduleEntity selectedNextEntity = cseC.orElseGet(() -> continueVariants.get(0));

        if (selectedNextEntity != null) {
            List<StationEntity> nextPath = new ArrayList<>(path);
            nextPath.remove(currentStation);
            List<CapsulesScheduleEntity> result = searchTripContinueVariants(requested, nextPath, selectedNextEntity);
            result.add(0, selectedNextEntity);
            return result;
        } else {
            return new ArrayList<>();
        }
    }


    public List<List<Pair<CapsulesScheduleEntity,CapsulesScheduleEntity>>> processRequest(String requested, String from, String to, String fromDateStr, String toDateStr) {
        List<List<CapsulesScheduleEntity>> result = searchTripVariants(
                Integer.parseInt(requested),
                stationService.getStationById(Integer.parseInt(from)),
                stationService.getStationById(Integer.parseInt(to)),
                TimeUtil.parseTimestampDate(fromDateStr),
                TimeUtil.parseTimestampDate(toDateStr));

        List<List<Pair<CapsulesScheduleEntity,CapsulesScheduleEntity>>> output = new ArrayList<>();
        for (List<CapsulesScheduleEntity> scheduleEntities : result) {
            List<Pair<CapsulesScheduleEntity,CapsulesScheduleEntity>> outputSchedule = new ArrayList<>();

            CapsulesScheduleEntity departureSchedule = scheduleEntities.get(0);
            CapsulesScheduleEntity prevCSE = scheduleEntities.get(0);
            CapsuleEntity capsule = departureSchedule.getCapsule();
            for (CapsulesScheduleEntity scheduleEntity : scheduleEntities) {
                if(!scheduleEntity.getCapsule().equals(capsule)){
                    outputSchedule.add(Pair.of(departureSchedule, prevCSE));
                    departureSchedule = scheduleEntity;
                    capsule = scheduleEntity.getCapsule();
                }
                prevCSE = scheduleEntity;
            }
            outputSchedule.add(Pair.of(departureSchedule, prevCSE));
            output.add(outputSchedule);
        }
        return output;
    }

    public List<List<CapsulesScheduleEntity>> searchTripVariants(int requested, StationEntity from, StationEntity to, Timestamp fromDate, Timestamp toDate) {
        List<StationEntity> path = getPath(from, to);
        List<CapsulesScheduleEntity> startVariants = capsuleScheduleService
                .findByNextStationsAndTimeBorders(fromDate, toDate, path.get(0), path.get(1))
                .stream().filter(__ -> capsuleScheduleService.hasFreeSeats(requested, __))
                .collect(Collectors.toList());
        return startVariants.stream().map((start) -> {
            List<StationEntity> nextPath = new ArrayList<>(path);
            nextPath.remove(path.get(0));
            List<CapsulesScheduleEntity> variant = searchTripContinueVariants(requested, nextPath, start);
            variant.add(0, start);
            return variant;
        }).filter(variant -> variant.size() == path.size() - 1)
                .collect(Collectors.toList());
    }

    /**
     * This method saves data about all stages where this passenger will be inside capsule in Traffic table.
     *
     * @param from   departure station
     * @param to     arrival station
     * @param ticket Bought ticket
     */
    public void persistTraffic(StationEntity from, StationEntity to, TicketEntity ticket) {

        List<StationEntity> stationsOfTrip = getPath(from, to);
        for (int i = 0; i < stationsOfTrip.size() - 1; i++) {
            TrafficEntity te = new TrafficEntity();
            te.setTripID(ticket.getTripID());
            te.setTicket(ticket);
            te.setFromStation(stationsOfTrip.get(i));
            te.setToStation(stationsOfTrip.get(i + 1));
            trafficService.add(te);
        }
    }

    public List<StationEntity> getPath(StationEntity from, StationEntity to) {
        List<StationEntity> stations = stationService.getStations();
        List<EdgeEntity> edges = edgeService.getEdges();

        Graph graph = new Graph(stations, edges);
        PathFinderService pathFinderService = new PathFinderService(graph);
        pathFinderService.execute(from); // start
        return pathFinderService.getPath(to); // end
    }
}
