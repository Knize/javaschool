package ru.knize.hyperloop.entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by knize on 03.09.16.
 */
@Entity
@Table(name = "Capsule", schema = "Hyperloop")
public class CapsuleEntity {
    private int id;
    private Integer seatsNumber;
    private Integer carSlots;
    private Collection<CapsulesScheduleEntity> capsulesSchedules;
    private Collection<TicketEntity> tickets;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int capsuleId) {
        this.id = capsuleId;
    }

    @Basic

    public Integer getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(Integer seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    @Basic

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

        if (id != that.id) return false;
        if (seatsNumber != null ? !seatsNumber.equals(that.seatsNumber) : that.seatsNumber != null) return false;
        if (carSlots != null ? !carSlots.equals(that.carSlots) : that.carSlots != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (seatsNumber != null ? seatsNumber.hashCode() : 0);
        result = 31 * result + (carSlots != null ? carSlots.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "capsule")
    public Collection<CapsulesScheduleEntity> getCapsulesSchedules() {
        return capsulesSchedules;
    }

    public void setCapsulesSchedules(Collection<CapsulesScheduleEntity> capsulesSchedulesByCapsuleId) {
        this.capsulesSchedules = capsulesSchedulesByCapsuleId;
    }

    @OneToMany(mappedBy = "capsule")
    public Collection<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<TicketEntity> ticketsByCapsuleId) {
        this.tickets = ticketsByCapsuleId;
    }

}
