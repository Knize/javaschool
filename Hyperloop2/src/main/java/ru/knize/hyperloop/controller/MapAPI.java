package ru.knize.hyperloop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.knize.hyperloop.DTO.EdgeDTO;
import ru.knize.hyperloop.DTO.StationDTOID;
import ru.knize.hyperloop.DTO.StationDTOWrapper;
import ru.knize.hyperloop.entities.StationEntity;
import ru.knize.hyperloop.services.EdgeService;
import ru.knize.hyperloop.services.StationService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * Created by knize on 06.10.16.
 */
@RestController
public class MapAPI {

    @Autowired
    StationService stationService;
    @Autowired
    EdgeService edgeService;

    @RequestMapping(method = RequestMethod.POST,
            value = "/api/stations/update",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void update(@RequestBody StationDTOWrapper stationDTOWrapper) {
        for (StationDTOID station : stationDTOWrapper.getUpdate()) {
            stationService.update(station);
        }
        for (StationDTOID station : stationDTOWrapper.getCreate()){
            stationService.add(station.toStationDTO());
        }
        for(Integer stationId : stationDTOWrapper.getDelete()){
            stationService.delete(stationId);
        }
        for(EdgeDTO edge : stationDTOWrapper.getEdges()){
            edgeService.addEdge(edge);
        }
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/api/stations/list",
            produces = APPLICATION_JSON_VALUE)
    public List<StationDTOID> getList() {
        List<StationEntity> stationsList = stationService.getStations();
        List<StationDTOID> stationDTOIDList = new ArrayList<>();
        for (StationEntity aStation : stationsList) {
            StationDTOID station = new StationDTOID();
            station.setId(Integer.toString(aStation.getId()));
            station.setName(aStation.getName());
            station.setTimezone(aStation.getTimezone());
            station.setLatitude(Double.toString(aStation.getLatitude()));
            station.setLongitude(Double.toString(aStation.getLongitude()));
            System.out.println(station);
            stationDTOIDList.add(station);
        }
        return stationDTOIDList;
    }


}


