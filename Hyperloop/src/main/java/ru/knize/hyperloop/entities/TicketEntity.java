package ru.knize.hyperloop.entities;

import javax.persistence.*;

/**
 * Created by knize on 28.08.16.
 */
@Entity
@Table(name = "Ticket", schema = "Hyperloop", catalog = "")
public class TicketEntity {
    private int ticketId;
    private Integer children;
    private Byte carSlot;
    private Double price;
    private Integer capsuleId;
    private Integer departureStationId;
    private Integer arrivalStationId;
    private Integer accountId;
    private Integer personId;

    @Id
    @Column(name = "Ticket_ID")
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

    @Basic
    @Column(name = "Capsule_ID")
    public Integer getCapsuleId() {
        return capsuleId;
    }

    public void setCapsuleId(Integer capsuleId) {
        this.capsuleId = capsuleId;
    }

    @Basic
    @Column(name = "Departure_Station_ID")
    public Integer getDepartureStationId() {
        return departureStationId;
    }

    public void setDepartureStationId(Integer departureStationId) {
        this.departureStationId = departureStationId;
    }

    @Basic
    @Column(name = "Arrival_Station_ID")
    public Integer getArrivalStationId() {
        return arrivalStationId;
    }

    public void setArrivalStationId(Integer arrivalStationId) {
        this.arrivalStationId = arrivalStationId;
    }

    @Basic
    @Column(name = "Account_ID")
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    @Basic
    @Column(name = "Person_ID")
    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }
}
