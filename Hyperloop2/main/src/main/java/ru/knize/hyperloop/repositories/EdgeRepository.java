package ru.knize.hyperloop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.knize.hyperloop.entities.EdgeEntity;

import java.util.List;

/**
 * Created by knize on 06.10.16.
 */
public interface EdgeRepository extends JpaRepository<EdgeEntity, Integer> {
    EdgeEntity findById(int id);

    @Query("SELECT e FROM EdgeEntity AS e WHERE e.branch.id = :branchId")
    List<EdgeEntity> edgesOfBranch(@Param("branchId") int branchId);

    @Query("SELECT e FROM EdgeEntity AS e WHERE e.fromStation.id = :stationId OR e.toStation.id = :stationId")
    List<EdgeEntity> edgesOfStation(@Param("stationId") int stationId);

    @Query("SELECT e FROM EdgeEntity AS e " +
            "WHERE (e.fromStation.id = :fromStationId AND e.toStation.id = :toStationId) " +
            "OR ((e.fromStation.id = :toStationId AND e.toStation.id = :fromStationId))")
    List<EdgeEntity> edgeByStationsId(@Param("fromStationId") int fromStationId, @Param("toStationId") int toStationId);
}