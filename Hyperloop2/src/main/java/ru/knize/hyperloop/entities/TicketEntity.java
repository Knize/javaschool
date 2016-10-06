package ru.knize.hyperloop.entities;

import javax.persistence.*;

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
        if (children != null ? !children.equals(that.children) : that.children != null) return false;
        if (carSlot != null ? !carSlot.equals(that.carSlot) : that.carSlot != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (children != null ? children.hashCode() : 0);
        result = 31 * result + (carSlot != null ? carSlot.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
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

    private long tripID;

    @Basic
    public long getTripID() {
        return tripID;
    }

    public void setTripID(long tripID) {
        this.tripID = tripID;
    }
}
