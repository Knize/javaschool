package ru.knize.hyperloop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.knize.hyperloop.entities.CapsulesScheduleEntity;
import ru.knize.hyperloop.entities.StationEntity;

import java.sql.Timestamp;
import java.util.List;

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


    @Query("SELECT s FROM CapsulesScheduleEntity AS s WHERE s.fromStation.id = :fromStation AND s.toStation.id = :toStationId")
    List<CapsulesScheduleEntity> findByStationsId(@Param("fromStation") int fromStation, @Param("toStation") int toStation);

    @Query("SELECT s FROM CapsulesScheduleEntity AS s WHERE s.fromStation.id = :fromStation")
    List<CapsulesScheduleEntity> findByFromStationId(@Param("fromStation") int fromStation);

    @Query("SELECT s FROM CapsulesScheduleEntity AS s WHERE s.toStation.id = :toStation")
    List<CapsulesScheduleEntity> findByToStationId(@Param("toStation") int toStation);

    @Query("SELECT s FROM CapsulesScheduleEntity AS s WHERE s.tripID = :tripId")
    List<CapsulesScheduleEntity> findByTripId(@Param("tripId") long tripId);

    @Query("SELECT s FROM CapsulesScheduleEntity AS s WHERE s.departureTime >= :depTime AND s.fromStation = :fromStation")
    List<CapsulesScheduleEntity> findByDepTime(@Param("depTime") Timestamp depTime, @Param("fromStation") StationEntity fromStation);

    @Query("SELECT s FROM CapsulesScheduleEntity AS s WHERE s.fromStation = :fromStation AND s.toStation = :toStation AND s.departureTime >= :depTime")
    List<CapsulesScheduleEntity>
    findByNextStationAndDepTime(@Param("depTime") Timestamp time,
                                @Param("fromStation") StationEntity fromStation,
                                @Param("toStation") StationEntity toStation);

    @Query("SELECT s FROM CapsulesScheduleEntity AS s " +
            "WHERE s.fromStation = :fromStation " +
            "AND s.toStation = :toStation " +
            "AND s.departureTime >= :fromTime " +
            "AND s.departureTime <= :toTime")
    List<CapsulesScheduleEntity>
    findByNextStationAndTimeBorders(@Param("fromTime") Timestamp fromTime,
                                    @Param("toTime") Timestamp toTime,
                                    @Param("fromStation") StationEntity fromStation,
                                    @Param("toStation") StationEntity toStation);

    CapsulesScheduleEntity findById(int id);
}


