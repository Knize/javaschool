package ru.knize.hyperloop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.knize.hyperloop.entities.CapsulesScheduleEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by knize on 30.09.16.
 */
public interface CapsuleScheduleRepository extends JpaRepository<CapsulesScheduleEntity, Integer> {

    default List<CapsulesScheduleEntity> findByCapsuleId(int capsuleId) {

        return findAll()
                .stream()
                .filter(cse -> cse.getCapsule().getId() == capsuleId)
                .collect(Collectors.toList());
    }

    default List<CapsulesScheduleEntity> findByStationId(int stationId){

        return findAll()
                .stream()
                .filter(cse -> cse.getStation().getId() == stationId)
                .collect(Collectors.toList());
    }

    CapsulesScheduleEntity findById(int id);
}


