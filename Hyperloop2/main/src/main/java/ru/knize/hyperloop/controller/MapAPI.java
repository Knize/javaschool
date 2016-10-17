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
    public void update(@RequestBody StationsEdgesUpdateInfo stationsEdgesUpdateInfo) {
        for (StationDTO station : stationsEdgesUpdateInfo.getStationsUpdate()) {
            stationService.update(station);
        }
        for (StationDTO station : stationsEdgesUpdateInfo.getStationsCreate()) {
            stationService.add(station);
        }
        for (Integer stationId : stationsEdgesUpdateInfo.getStationsDelete()) {
            stationService.delete(stationId);
        }
        for (EdgeDTO edge : stationsEdgesUpdateInfo.getEdgesCreate()) {
            edgeService.add(edge);
        }
        for (EdgeDTO edge : stationsEdgesUpdateInfo.getEdgesUpdate()) {
            edgeService.update(edge);
        }
        for (Integer edgeId : stationsEdgesUpdateInfo.getEdgesDelete()) {
            edgeService.delete(edgeId);
        }
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/api/stations/list",
            produces = APPLICATION_JSON_VALUE)
    public StationsEdgesInfo getList() {
        StationsEdgesInfo stationsEdgesInfo = new StationsEdgesInfo();
        List<StationEntity> stationsList = stationService.getStations();
        List<StationDTO> stationDTOList = new ArrayList<>();
        for (StationEntity aStation : stationsList) {
            StationDTO station = new StationDTO();
            station.setId(Integer.toString(aStation.getId()));
            station.setName(aStation.getName());
            station.setTimezone(aStation.getTimezone());
            station.setLatitude(Double.toString(aStation.getLatitude()));
            station.setLongitude(Double.toString(aStation.getLongitude()));
            station.setEndForBranch(aStation.getEndForBranch());
            stationDTOList.add(station);
        }
        List<EdgeEntity> edgeList = edgeService.getEdges();
        List<EdgeDTO> edgeDTOList = new ArrayList<>();
        for (EdgeEntity edge : edgeList) {
            EdgeDTO edgeDTO = new EdgeDTO();
            edgeDTO.setId(edge.getId());
            edgeDTO.setFromStationId(edge.getFromStation().getId());
            edgeDTO.setToStationId(edge.getToStation().getId());
            edgeDTO.setBranchId(edge.getBranch().getId());
            edgeDTO.setRangeKm(edge.getRangeKm());
            edgeDTOList.add(edgeDTO);
        }
        stationsEdgesInfo.setStations(stationDTOList);
        stationsEdgesInfo.setEdges(edgeDTOList);

        return stationsEdgesInfo;
    }


}


