package ru.knize.hyperloop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.knize.hyperloop.DTO.StationDTO;
import ru.knize.hyperloop.entities.StationEntity;
import ru.knize.hyperloop.repositories.StationRepository;

import java.util.List;

/**
 * Created by knize on 04.10.16.
 */
@Service
public class StationService {
    @Autowired
    StationRepository stationRepository;
    @Autowired
    BranchService branchService;

    @Transactional
    public List<StationEntity> getStations() {
        return stationRepository.findAll();
    }

    @Transactional
    public StationEntity getStationById(int stationId) {
        return stationRepository.findById(stationId);
    }

    @Transactional
    public void addStation(StationDTO stationDTO){
        StationEntity se = new StationEntity();
        se.setRangeKm(stationDTO.getRangeKm());
        se.setTimezone(stationDTO.getTimezone());
        se.setLongitude(0);
        se.setLatitude(0);
        se.setName(stationDTO.getName());

        stationRepository.save(se);
    }


}
