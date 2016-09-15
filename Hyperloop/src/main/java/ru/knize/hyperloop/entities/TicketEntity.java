package ru.knize.hyperloop.entities;

import javax.persistence.*;

/**
 * Created by knize on 03.09.16.
 */
@Entity
@Table(name = "Ticket", schema = "Hyperloop", catalog = "")
public class TicketEntity {
    private int ticketId;
    private Integer children;
    private Byte carSlot;
    private Double price;
    private CapsuleEntity capsuleByCapsuleId;
    private StationEntity stationByDepartureStationId;
    private StationEntity stationByArrivalStationId;
    private AccountEntity accountByAccountId;
    private PersonEntity personByPersonId;

    @Id
    @Column(name = "Ticket_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    @Basic
    @Column(name = "Children")
    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    @Basic
    @Column(name = "Car_Slot")
    public Byte getCarSlot() {
        return carSlot;
    }

    public void setCarSlot(Byte carSlot) {
        this.carSlot = carSlot;
    }

    @Basic
    @Column(name = "Price")
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

        if (ticketId != that.ticketId) return false;
      if (children != null ? !children.equals(that.children) : that.children != null) return false;
        if (carSlot != null ? !carSlot.equals(that.carSlot) : that.carSlot != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ticketId;
        result = 31 * result + (children != null ? children.hashCode() : 0);
        result = 31 * result + (carSlot != null ? carSlot.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Capsule_ID", referencedColumnName = "Capsule_ID")
    public CapsuleEntity getCapsuleByCapsuleId() {
        return capsuleByCapsuleId;
    }

    public void setCapsuleByCapsuleId(CapsuleEntity capsuleByCapsuleId) {
        this.capsuleByCapsuleId = capsuleByCapsuleId;
    }

    @ManyToOne
    @JoinColumn(name = "Departure_Station_ID", referencedColumnName = "Station_ID")
    public StationEntity getStationByDepartureStationId() {
        return stationByDepartureStationId;
    }

    public void setStationByDepartureStationId(StationEntity stationByDepartureStationId) {
        this.stationByDepartureStationId = stationByDepartureStationId;
    }

    @ManyToOne
    @JoinColumn(name = "Arrival_Station_ID", referencedColumnName = "Station_ID")
    public StationEntity getStationByArrivalStationId() {
        return stationByArrivalStationId;
    }

    public void setStationByArrivalStationId(StationEntity stationByArrivalStationId) {
        this.stationByArrivalStationId = stationByArrivalStationId;
    }

    @ManyToOne
    @JoinColumn(name = "Account_ID", referencedColumnName = "Account_ID")
    public AccountEntity getAccountByAccountId() {
        return accountByAccountId;
    }

    public void setAccountByAccountId(AccountEntity accountByAccountId) {
        this.accountByAccountId = accountByAccountId;
    }

    @ManyToOne
    @JoinColumn(name = "Person_ID", referencedColumnName = "Person_ID")
    public PersonEntity getPersonByPersonId() {
        return personByPersonId;
    }

    public void setPersonByPersonId(PersonEntity personByPersonId) {
        this.personByPersonId = personByPersonId;
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
