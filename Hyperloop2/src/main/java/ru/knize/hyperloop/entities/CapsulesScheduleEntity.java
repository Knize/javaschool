package ru.knize.hyperloop.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by knize on 03.09.16.
 */
@Entity
@Table(name = "Capsules_Schedule", schema = "Hyperloop", catalog = "")
public class CapsulesScheduleEntity {


    private Timestamp departureTime;
    private Timestamp arrivalTime;
    private CapsuleEntity capsule;
    private StationEntity station;
    private int id;

    @Basic

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    @Basic

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CapsulesScheduleEntity that = (CapsulesScheduleEntity) o;

        if (id != that.id) return false;
        if (departureTime != null ? !departureTime.equals(that.departureTime) : that.departureTime != null)
            return false;
        if (arrivalTime != null ? !arrivalTime.equals(that.arrivalTime) : that.arrivalTime != null) return false;
        if (capsule != null ? !capsule.equals(that.capsule) : that.capsule != null)
            return false;
        return station != null ? station.equals(that.station) : that.station == null;

    }

    @Override
    public int hashCode() {
        int result = departureTime != null ? departureTime.hashCode() : 0;
        result = 31 * result + (arrivalTime != null ? arrivalTime.hashCode() : 0);
        result = 31 * result + (capsule != null ? capsule.hashCode() : 0);
        result = 31 * result + (station != null ? station.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }

    @ManyToOne
    @JoinColumn(nullable = false)
    public CapsuleEntity getCapsule() {
        return capsule;
    }

    public void setCapsule(CapsuleEntity capsuleByCapsuleId) {
        this.capsule = capsuleByCapsuleId;
    }

    @ManyToOne
    @JoinColumn(nullable = false)
    public StationEntity getStation() {
        return station;
    }

    public void setStation(StationEntity stationByStationId) {
        this.station = stationByStationId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int capsuleScheduleId) {
        this.id = capsuleScheduleId;
    }

    private int tripType;

    @Basic
    public int getTripType() {
        return tripType;
    }

    public void setTripType(int tripType) {
        this.tripType = tripType;
    }

    private boolean direction;

    @Basic
    public boolean isDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    private long tripID;

    @Basic
    public long getTripID() {
        return tripID;
    }

    public void setTripID(long trip_ID) {
        this.tripID = trip_ID;
    }
}
