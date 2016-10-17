package ru.knize.hyperloop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.knize.hyperloop.entities.StationEntity;
import ru.knize.hyperloop.entities.TrafficEntity;

/**
 * Created by knize on 05.10.16.
 */
public interface TrafficRepository extends JpaRepository<TrafficEntity, Integer> {

    TrafficEntity findByTripID(long tripID);

    @Query("SELECT COUNT(t) FROM TrafficEntity AS t WHERE t.tripID = :tripId AND t.fromStation = :fromStation")
    int getTraffic(@Param("tripId") long tripId, @Param("fromStation") StationEntity fromStation);
}