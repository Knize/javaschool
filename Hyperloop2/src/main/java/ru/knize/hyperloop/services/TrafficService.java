package ru.knize.hyperloop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.knize.hyperloop.entities.StationEntity;
import ru.knize.hyperloop.entities.TrafficEntity;
import ru.knize.hyperloop.repositories.TrafficRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by knize on 05.10.16.
 */
@Service
public class TrafficService {
    @Autowired
    TrafficRepository trafficRepository;

    @Transactional
    public List<TrafficEntity> getTraffic(
            StationEntity fromStation,
            StationEntity toStation,
            long tripId){
        List<TrafficEntity> trafficList = trafficRepository.findAll();
        trafficList.stream().filter(trafficEntry ->
                trafficEntry.getFromStation().getStationId() == fromStation.getStationId() &&
                trafficEntry.getToStation().getStationId() == toStation.getStationId() &&
                trafficEntry.getTripID() == tripId).
                collect(Collectors.toList());
        return trafficList;
    }

}
