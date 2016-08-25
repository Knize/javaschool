package ru.knize.hyperloop.entities;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by knize on 23.08.16.
 */
@Entity
@Table(name = "Capsules_Schedule", schema = "Hyperloop")
@IdClass(CapsulesScheduleEntityPK.class)
public class CapsulesScheduleEntity {
    private int capsuleId;
    private int stationId;
    private Time departureTime;
    private Time arrivalTime;

    @Id
    @Column(name = "Capsule_ID", nullable = false)
    public int getCapsuleId() {
        return capsuleId;
    }

    public void setCapsuleId(int capsuleId) {
        this.capsuleId = capsuleId;
    }

    @Id
    @Column(name = "Station_ID", nullable = false)
    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    @Basic
    @Column(name = "Departure_Time", nullable = true)
    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    @Basic
    @Column(name = "Arrival_Time", nullable = true)
    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CapsulesScheduleEntity that = (CapsulesScheduleEntity) o;

        if (capsuleId != that.capsuleId) return false;
        if (stationId != that.stationId) return false;
        if (departureTime != null ? !departureTime.equals(that.departureTime) : that.departureTime != null)
            return false;
        if (arrivalTime != null ? !arrivalTime.equals(that.arrivalTime) : that.arrivalTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = capsuleId;
        result = 31 * result + stationId;
        result = 31 * result + (departureTime != null ? departureTime.hashCode() : 0);
        result = 31 * result + (arrivalTime != null ? arrivalTime.hashCode() : 0);
        return result;
    }
}
