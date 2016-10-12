package ru.knize.hyperloop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.knize.hyperloop.DTO.StationDTO;
import ru.knize.hyperloop.DTO.StationDTOID;
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
    public void add(StationDTO stationDTO){
        StationEntity se = new StationEntity();
        se.setTimezone(stationDTO.getTimezone());
        se.setLongitude(0);
        se.setLatitude(0);
        se.setName(stationDTO.getName());

        stationRepository.save(se);
    }

    @Transactional
    public void delete(int stationId){
        stationRepository.delete(getStationById(stationId));
    }

    @Transactional
    public void update(StationDTOID stationDTOID){
        StationEntity se = new StationEntity();
        se.setId(stationDTOID.getId());
        se.setTimezone(stationDTOID.getTimezone());
        se.setLongitude(stationDTOID.getLongitude());
        se.setLatitude(stationDTOID.getLatitude());
        se.setName(stationDTOID.getName());
        System.out.println("Going to persist station: " + se);
        stationRepository.save(se);
    }


}
