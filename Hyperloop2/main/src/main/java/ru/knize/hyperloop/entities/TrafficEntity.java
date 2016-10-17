package ru.knize.hyperloop.entities;

import javax.persistence.*;

/**
 * Created by knize on 14.09.16.
 */
@Entity
public class TrafficEntity {
    private long tripID;
    private long id;
    private StationEntity FromStation;
    private StationEntity toStation;
    private EdgeEntity edge;
    private TicketEntity ticket;

    @ManyToOne
    public EdgeEntity getEdge() {
        return edge;
    }



    public void setEdge(EdgeEntity edge) {
        this.edge = edge;
    }

    @Basic
    public long getTripID() {
        return tripID;
    }

    public void setTripID(long tripID) {
        this.tripID = tripID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    public StationEntity getFromStation() {
        return FromStation;
    }

    public void setFromStation(StationEntity fromStation) {
        FromStation = fromStation;
    }

    @ManyToOne
    public StationEntity getToStation() {
        return toStation;
    }

    public void setToStation(StationEntity toStation) {
        this.toStation = toStation;
    }

    @ManyToOne
    public TicketEntity getTicket() {
        return ticket;
    }

    public void setTicket(TicketEntity ticket) {
        this.ticket = ticket;
    }
}
