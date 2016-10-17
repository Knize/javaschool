package ru.knize.hyperloop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.knize.hyperloop.entities.StationEntity;

import java.util.List;

/**
 * Created by knize on 04.10.16.
 */
public interface StationRepository extends JpaRepository<StationEntity, Integer> {

    StationEntity findById(int id);

    @Query("SELECT s FROM StationEntity AS s WHERE s.endForBranch >= 0")
    List<StationEntity> endStations();

    @Query("SELECT s FROM StationEntity AS s WHERE s.endForBranch = :branchId")
    List<StationEntity> endStationsOfBranch(@Param("branchId") int branchId);

}
