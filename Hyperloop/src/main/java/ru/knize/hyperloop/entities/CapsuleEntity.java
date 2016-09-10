package ru.knize.hyperloop.entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by knize on 03.09.16.
 */
@Entity
@Table(name = "Capsule", schema = "Hyperloop", catalog = "")
public class CapsuleEntity {
    private int capsuleId;
    private Integer seatsNumber;
    private Integer carSlots;
    private Collection<CapsulesScheduleEntity> capsulesSchedulesByCapsuleId;
    private Collection<TicketEntity> ticketsByCapsuleId;

    @Id
    @Column(name = "Capsule_ID")
    public int getCapsuleId() {
        return capsuleId;
    }

    public void setCapsuleId(int capsuleId) {
        this.capsuleId = capsuleId;
    }

    @Basic
    @Column(name = "Seats_Number")
    public Integer getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(Integer seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    @Basic
    @Column(name = "Car_Slots")
    public Integer getCarSlots() {
        return carSlots;
    }

    public void setCarSlots(Integer carSlots) {
        this.carSlots = carSlots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CapsuleEntity that = (CapsuleEntity) o;

        if (capsuleId != that.capsuleId) return false;
        if (seatsNumber != null ? !seatsNumber.equals(that.seatsNumber) : that.seatsNumber != null) return false;
        if (carSlots != null ? !carSlots.equals(that.carSlots) : that.carSlots != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = capsuleId;
        result = 31 * result + (seatsNumber != null ? seatsNumber.hashCode() : 0);
        result = 31 * result + (carSlots != null ? carSlots.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "capsuleByCapsuleId")
    public Collection<CapsulesScheduleEntity> getCapsulesSchedulesByCapsuleId() {
        return capsulesSchedulesByCapsuleId;
    }

    public void setCapsulesSchedulesByCapsuleId(Collection<CapsulesScheduleEntity> capsulesSchedulesByCapsuleId) {
        this.capsulesSchedulesByCapsuleId = capsulesSchedulesByCapsuleId;
    }

    @OneToMany(mappedBy = "capsuleByCapsuleId")
    public Collection<TicketEntity> getTicketsByCapsuleId() {
        return ticketsByCapsuleId;
    }

    public void setTicketsByCapsuleId(Collection<TicketEntity> ticketsByCapsuleId) {
        this.ticketsByCapsuleId = ticketsByCapsuleId;
    }

}
