package ru.knize.hyperloop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.knize.hyperloop.DTO.EdgeDTO;
import ru.knize.hyperloop.entities.BranchEntity;
import ru.knize.hyperloop.entities.EdgeEntity;
import ru.knize.hyperloop.entities.StationEntity;
import ru.knize.hyperloop.repositories.EdgeRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by knize on 06.10.16.
 */
@Service
public class EdgeService {

    @Autowired
    EdgeRepository edgeRepository;
    @Autowired
    StationService stationService;
    @Autowired
    BranchService branchService;


    public EdgeEntity getEdgeById(int edgeId) {
        return edgeRepository.findById(edgeId);
    }

    public List<EdgeEntity> getEdges() {
        return edgeRepository.findAll();
    }


    public EdgeEntity getEdgeByStationsId(int fromStationId, int toStationId) {
        List<EdgeEntity> list = new ArrayList<>();
        list.addAll(edgeRepository.edgeByStationsId(fromStationId, toStationId));
        return list.get(0);
    }

    public void add(EdgeDTO edgeDTO) {
        EdgeEntity edge = new EdgeEntity();
        StationEntity fromStation = stationService.getStationById(edgeDTO.getFromStationId());
        StationEntity toStation = stationService.getStationById(edgeDTO.getToStationId());
        BranchEntity branch = branchService.getBranchById(edgeDTO.getBranchId());
        System.out.println(edgeDTO);
        edge.setRangeKm(edgeDTO.getRangeKm());
        edge.setFromStation(fromStation);
        edge.setToStation(toStation);
        edge.setBranch(branch);
        System.out.println("From station id: " + fromStation.getId());
        System.out.println("To station id: " + toStation.getId());
        edgeRepository.save(edge);
    }

    public void update(EdgeDTO edgeDTO) {
        EdgeEntity edge = new EdgeEntity();
        StationEntity fromStation = stationService.getStationById(edgeDTO.getFromStationId());
        StationEntity toStation = stationService.getStationById(edgeDTO.getToStationId());
        BranchEntity branch = branchService.getBranchById(edgeDTO.getBranchId());
        System.out.println(edgeDTO);
        edge.setId(edgeDTO.getId());
        edge.setRangeKm(edgeDTO.getRangeKm());
        edge.setFromStation(fromStation);
        edge.setToStation(toStation);
        edge.setBranch(branch);
        System.out.println("From station id: " + fromStation.getId());
        System.out.println("To station id: " + toStation.getId());
        edgeRepository.save(edge);
    }

    public void delete(int edgeId) {
        EdgeEntity edge = edgeRepository.findById(edgeId);
        edgeRepository.delete(edge);
    }

    public EdgeEntity edgeByStation(StationEntity station) {
        return edgeRepository.edgesOfStation(station.getId()).get(0);
    }

    public List<EdgeEntity> getBranch(int branchId) {
        return edgeRepository.edgesOfBranch(branchId);
    }

}
