package ru.knize.hyperloop.entities;

import javax.persistence.*;

/**
 * Created by knize on 14.09.16.
 */
@Entity
public class TrafficEntity {
    private long tripID;

    @Basic
    public long getTripID() {
        return tripID;
    }

    public void setTripID(long tripID) {
        this.tripID = tripID;
    }

    private long id;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private StationEntity FromStation;

    @ManyToOne
    public StationEntity getFromStation() {
        return FromStation;
    }

    public void setFromStation(StationEntity fromStation) {
        FromStation = fromStation;
    }

    private StationEntity toStation;

    @ManyToOne
    public StationEntity getToStation() {
        return toStation;
    }

    public void setToStation(StationEntity toStation) {
        this.toStation = toStation;
    }

    private TicketEntity ticket;

    @ManyToOne
    public TicketEntity getTicket() {
        return ticket;
    }

    public void setTicket(TicketEntity ticket) {
        this.ticket = ticket;
    }
}
