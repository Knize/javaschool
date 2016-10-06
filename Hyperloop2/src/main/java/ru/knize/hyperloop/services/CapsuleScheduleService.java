package ru.knize.hyperloop.services;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.knize.hyperloop.DTO.CapsuleScheduleDTO;
import ru.knize.hyperloop.entities.CapsuleEntity;
import ru.knize.hyperloop.entities.CapsulesScheduleEntity;
import ru.knize.hyperloop.entities.StationEntity;
import ru.knize.hyperloop.repositories.CapsuleScheduleRepository;
import ru.knize.hyperloop.util.CapsuleTravelingMath;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

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

    @Transactional
    CapsulesScheduleEntity getScheduleById(int scheduleId) {
        return capsuleScheduleRepository.findById(scheduleId);
    }

    @Transactional
    List<CapsulesScheduleEntity> getScheduleList() {
        return Lists.newArrayList(capsuleScheduleRepository.findAll());
    }

    @Transactional
    public CapsulesScheduleEntity addSchedule(CapsulesScheduleEntity cse) {
        cse = capsuleScheduleRepository.save(cse);
        capsuleScheduleRepository.flush();
        return cse;
    }

    @Transactional
    public List<CapsulesScheduleEntity> getByStationId(int stationId) {
        return capsuleScheduleRepository.findByStationId(stationId);
    }

    @Transactional
    public List<CapsulesScheduleEntity> getByCapsuleId(int capsuleId) {
        return capsuleScheduleRepository.findByCapsuleId(capsuleId);
    }

    public CapsulesScheduleEntity createCapsuleScheduleEntity(CapsuleScheduleDTO csDTO) {
        CapsulesScheduleEntity cse = new CapsulesScheduleEntity();
        try {
            int capsuleId = Integer.parseInt(csDTO.getCapsuleId());
            int stationId = Integer.parseInt(csDTO.getStationId());
            int direction = Integer.parseInt(csDTO.getDirection());
            CapsuleEntity capsule = capsuleService.getCapsuleById(capsuleId);
            StationEntity station = stationService.getStationById(stationId);

            String timestampStr = csDTO.getDate() + " " + csDTO.getTime();
            SimpleDateFormat timestampFormat = new SimpleDateFormat("dd MM, yyyy hh:mm");

            Date timestampDate = timestampFormat.parse(timestampStr);

            long time = timestampDate.getTime();
            Timestamp timestampArrival = new Timestamp(time);
            Timestamp timestampDeparture = new Timestamp(timestampArrival.getTime() + +300 * 1000L);

            cse.setDirection(direction == 1);
            cse.setTripType(1);
            cse.setCapsule(capsule);
            cse.setStation(station);
            cse.setArrivalTime(timestampArrival);
            cse.setDepartureTime(timestampDeparture);
            java.util.Date date = new java.util.Date();
            long tripUID = date.getTime();
            cse.setTripID(tripUID);
            //fillSchedule(cse, session, tripUID);
        } catch (ParseException | NumberFormatException e) {

        }

        return cse;

    }




}
