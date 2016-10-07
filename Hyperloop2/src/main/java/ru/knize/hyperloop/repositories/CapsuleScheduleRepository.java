package ru.knize.hyperloop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.knize.hyperloop.entities.CapsulesScheduleEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by knize on 30.09.16.
 */
public interface CapsuleScheduleRepository extends JpaRepository<CapsulesScheduleEntity, Integer> {
/*
    default List<CapsulesScheduleEntity> findByCapsuleId(int capsuleId) {

        return findAll()
                .stream()
                .filter(cse -> cse.getCapsule().getId() == capsuleId)
                .collect(Collectors.toList());
    }
*/

    @Query("SELECT s FROM CapsulesScheduleEntity AS s WHERE s.capsule.id = :capsuleId")
    List<CapsulesScheduleEntity> findByCapsuleId(@Param("capsuleId") int capsuleId);


    @Query("SELECT s FROM CapsulesScheduleEntity AS s WHERE s.station.id = :stationId")
    List<CapsulesScheduleEntity> findByStationId(@Param("stationId") int stationId);

    /*
    default List<CapsulesScheduleEntity> findByStationId(int stationId){
        return findAll()
                .stream()
                .filter(cse -> cse.getStation().getId() == stationId)
                .collect(Collectors.toList());
    }
    */

    CapsulesScheduleEntity findById(int id);
}


