package ru.knize.hyperloop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.knize.hyperloop.entities.TicketEntity;
import ru.knize.hyperloop.repositories.TicketRepository;

import java.util.List;

/**
 * Created by knize on 05.10.16.
 */
@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Transactional
    public List<TicketEntity> getTickets(){ return ticketRepository.findAll();}



}
