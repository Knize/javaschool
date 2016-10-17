package ru.knize.hyperloop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.knize.hyperloop.entities.TicketEntity;

/**
 * Created by knize on 05.10.16.
 */
public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {

    TicketEntity findByTripID(int tripID);
}