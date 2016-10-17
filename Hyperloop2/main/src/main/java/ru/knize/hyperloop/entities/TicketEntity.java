package ru.knize.hyperloop.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by knize on 03.09.16.
 */

@Entity
@Table(name = "Ticket", schema = "Hyperloop", catalog = "")
public class TicketEntity {
    private int id;
    private Integer children;
    private Byte carSlot;
    private Double price;
    private CapsuleEntity capsule;
    private StationEntity fromStation;
    private StationEntity toStation;
    private AccountEntity account;
    private PersonEntity person;
    private Timestamp datetime;
    private long tripID;

    @Basic
    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int ticketId) {
        this.id = ticketId;
    }

    @Basic
    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    @Basic
    public Byte getCarSlot() {
        return carSlot;
    }

    public void setCarSlot(Byte carSlot) {
        this.carSlot = carSlot;
    }

    @Basic
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketEntity that = (TicketEntity) o;
        if (id != that.id) return false;
        if (tripID != that.tripID) return false;
        if (!children.equals(that.children)) return false;
        if (!carSlot.equals(that.carSlot)) return false;
        if (!price.equals(that.price)) return false;
        if (!capsule.equals(that.capsule)) return false;
        if (!fromStation.equals(that.fromStation)) return false;
        if (!toStation.equals(that.toStation)) return false;
        if (!account.equals(that.account)) return false;
        if (!person.equals(that.person)) return false;
        return datetime.equals(that.datetime);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + children.hashCode();
        result = 31 * result + carSlot.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + capsule.hashCode();
        result = 31 * result + fromStation.hashCode();
        result = 31 * result + toStation.hashCode();
        result = 31 * result + account.hashCode();
        result = 31 * result + person.hashCode();
        result = 31 * result + datetime.hashCode();
        result = 31 * result + (int) (tripID ^ (tripID >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn()
    public CapsuleEntity getCapsule() {
        return capsule;
    }

    public void setCapsule(CapsuleEntity capsuleByCapsuleId) {
        this.capsule = capsuleByCapsuleId;
    }

    @ManyToOne
    @JoinColumn()
    public StationEntity getFromStation() {
        return fromStation;
    }

    public void setFromStation(StationEntity stationByDepartureStationId) {
        this.fromStation = stationByDepartureStationId;
    }

    @ManyToOne
    @JoinColumn()
    public StationEntity getToStation() {
        return toStation;
    }

    public void setToStation(StationEntity stationByArrivalStationId) {
        this.toStation = stationByArrivalStationId;
    }

    @ManyToOne
    @JoinColumn()
    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity accountByAccountId) {
        this.account = accountByAccountId;
    }

    @ManyToOne
    @JoinColumn()
    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity personByPersonId) {
        this.person = personByPersonId;
    }

    @Basic
    public long getTripID() {
        return tripID;
    }

    public void setTripID(long tripID) {
        this.tripID = tripID;
    }
}
