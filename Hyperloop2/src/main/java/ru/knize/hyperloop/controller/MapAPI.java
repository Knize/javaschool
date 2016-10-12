package ru.knize.hyperloop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.knize.hyperloop.DTO.*;
import ru.knize.hyperloop.entities.EdgeEntity;
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
        for (StationDTOID station : stationDTOWrapper.getStationsUpdate()) {
            stationService.update(station);
        }
        for (StationDTOID station : stationDTOWrapper.getStationsCreate()) {
            stationService.add(station.toStationDTO());
        }
        for (Integer stationId : stationDTOWrapper.getStationsDelete()) {
            stationService.delete(stationId);
        }
        for (EdgeDTO edge : stationDTOWrapper.getEdgesCreate()) {
            edgeService.add(edge);
        }
        for (EdgeDTO edge : stationDTOWrapper.getEdgesUpgrade()) {
            edgeService.update(edge);
        }
        for (Integer edgeId : stationDTOWrapper.getEdgesDelete()) {
            edgeService.delete(edgeId);
        }
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/api/stations/list",
            produces = APPLICATION_JSON_VALUE)
    public StationEdgeDTOWrapper getList() {
        StationEdgeDTOWrapper stationEdgeDTOWrapper = new StationEdgeDTOWrapper();
        List<StationEntity> stationsList = stationService.getStations();
        List<StationDTOID> stationDTOIDList = new ArrayList<>();
        for (StationEntity aStation : stationsList) {
            StationDTOID station = new StationDTOID();
            station.setId(Integer.toString(aStation.getId()));
            station.setName(aStation.getName());
            station.setTimezone(aStation.getTimezone());
            station.setLatitude(Double.toString(aStation.getLatitude()));
            station.setLongitude(Double.toString(aStation.getLongitude()));
            stationDTOIDList.add(station);
        }
        List<EdgeEntity> edgeList = edgeService.getEdges();
        List<EdgeDTO> edgeDTOList = new ArrayList<>();
        for (EdgeEntity edge : edgeList) {
            EdgeDTO edgeDTO = new EdgeDTO();
            edgeDTO.setId(edge.getId());
            edgeDTO.setFromStation(StationDTOID.fromStationEntity(edge.getFromStation()));
            edgeDTO.setToStation(StationDTOID.fromStationEntity(edge.getToStation()));
            edgeDTO.setBranch(BranchDTO.fromBranchEntity(edge.getBranch()));
            edgeDTO.setRangeKm(edge.getRangeKm());
            edgeDTOList.add(edgeDTO);
        }
        stationEdgeDTOWrapper.setStations(stationDTOIDList);
        stationEdgeDTOWrapper.setEdges(edgeDTOList);

        return stationEdgeDTOWrapper;
    }


}


