package ru.knize.hyperloop.ticketsReport.DTO;



import java.util.List;

/**
 * Created by knize on 15.10.16.
 */
public class TicketsInfo {
    List<TicketDTO> tickets;

    public TicketsInfo() {
    }

    @Override
    public String toString() {
        return "TicketsInfo{" +
                "tickets=" + tickets +
                '}';
    }

    public List<TicketDTO> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDTO> tickets) {
        this.tickets = tickets;
    }
}
