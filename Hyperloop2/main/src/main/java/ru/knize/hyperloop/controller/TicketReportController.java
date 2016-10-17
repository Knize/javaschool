package ru.knize.hyperloop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.knize.hyperloop.entities.TicketEntity;
import ru.knize.hyperloop.services.TicketService;
import ru.knize.hyperloop.ticketsReport.DTO.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * Created by knize on 15.10.16.
 */
@RestController
public class TicketReportController {

    @Autowired
    TicketService ticketService;

    @RequestMapping(method = RequestMethod.GET,
            value = "/api/tickets/list",
            produces = APPLICATION_JSON_VALUE)
    public TicketsInfo getTickets() {
        TicketsInfo ticketsInfo = new TicketsInfo();
        List<TicketEntity> tickets = ticketService.getTickets();
        List<ru.knize.hyperloop.ticketsReport.DTO.TicketDTO> ticketDTOList = new ArrayList<>();
        for (TicketEntity t : tickets) {
            ru.knize.hyperloop.ticketsReport.DTO.TicketDTO ticketDTO = new ru.knize.hyperloop.ticketsReport.DTO.TicketDTO();
            ticketDTO.setId(t.getId());
            ticketDTO.setAccountId(t.getAccount().getId());
            ticketDTO.setCapsuleId(t.getCapsule().getId());
            ticketDTO.setCarSlot(t.getCarSlot() == 1);
            ticketDTO.setFromStationId(t.getFromStation().getId());
            ticketDTO.setToStationId(t.getToStation().getId());
            ticketDTO.setPersonId(t.getPerson().getId());
            ticketDTO.setPrice(t.getPrice());
            ticketDTO.setTripId(t.getTripID());
            ticketDTOList.add(ticketDTO);
        }
        ticketsInfo.setTickets(ticketDTOList);
        return ticketsInfo;
    }
}
