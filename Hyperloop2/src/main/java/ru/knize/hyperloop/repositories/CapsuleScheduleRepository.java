package ru.knize.hyperloop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.knize.hyperloop.entities.CapsulesScheduleEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by knize on 30.09.16.
 */
public interface CapsuleScheduleRepository extends JpaRepository<CapsulesScheduleEntity, Integer> {

    default List<CapsulesScheduleEntity> findByCapsuleByCapsuleId(int capsuleId) {

        return findAll()
                .stream()
                .filter(cse -> cse.getCapsuleByCapsuleId().getCapsuleId() == capsuleId)
                .collect(Collectors.toList());
    }

    default List<CapsulesScheduleEntity> findByStationByStationId(int stationId){

        return findAll()
                .stream()
                .filter(cse -> cse.getStationByStationId().getStationId() == stationId)
                .collect(Collectors.toList());
    }

    CapsulesScheduleEntity findByCapsuleScheduleId(int id);
}


