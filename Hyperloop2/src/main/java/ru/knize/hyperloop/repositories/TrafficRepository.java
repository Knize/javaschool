package ru.knize.hyperloop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.knize.hyperloop.entities.TicketEntity;
import ru.knize.hyperloop.entities.TrafficEntity;

/**
 * Created by knize on 05.10.16.
 */
public interface TrafficRepository extends JpaRepository<TrafficEntity, Integer> {

    TrafficEntity findByTripID(int tripID);
}