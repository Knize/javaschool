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
    private CapsuleEntity capsuleByCapsuleId;
    private StationEntity stationByStationId;
    private int capsuleScheduleId;

    @Basic
    @Column(name = "Departure_Time")
    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    @Basic
    @Column(name = "Arrival_Time")
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

        if (capsuleScheduleId != that.capsuleScheduleId) return false;
        if (departureTime != null ? !departureTime.equals(that.departureTime) : that.departureTime != null)
            return false;
        if (arrivalTime != null ? !arrivalTime.equals(that.arrivalTime) : that.arrivalTime != null) return false;
        if (capsuleByCapsuleId != null ? !capsuleByCapsuleId.equals(that.capsuleByCapsuleId) : that.capsuleByCapsuleId != null)
            return false;
        return stationByStationId != null ? stationByStationId.equals(that.stationByStationId) : that.stationByStationId == null;

    }

    @Override
    public int hashCode() {
        int result = departureTime != null ? departureTime.hashCode() : 0;
        result = 31 * result + (arrivalTime != null ? arrivalTime.hashCode() : 0);
        result = 31 * result + (capsuleByCapsuleId != null ? capsuleByCapsuleId.hashCode() : 0);
        result = 31 * result + (stationByStationId != null ? stationByStationId.hashCode() : 0);
        result = 31 * result + capsuleScheduleId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Capsule_ID", referencedColumnName = "Capsule_ID", nullable = false)
    public CapsuleEntity getCapsuleByCapsuleId() {
        return capsuleByCapsuleId;
    }

    public void setCapsuleByCapsuleId(CapsuleEntity capsuleByCapsuleId) {
        this.capsuleByCapsuleId = capsuleByCapsuleId;
    }

    @ManyToOne
    @JoinColumn(name = "Station_ID", referencedColumnName = "Station_ID", nullable = false)
    public StationEntity getStationByStationId() {
        return stationByStationId;
    }

    public void setStationByStationId(StationEntity stationByStationId) {
        this.stationByStationId = stationByStationId;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getCapsuleScheduleId() {
        return capsuleScheduleId;
    }

    public void setCapsuleScheduleId(int capsuleScheduleId) {
        this.capsuleScheduleId = capsuleScheduleId;
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

    private long trip_ID;

    @Basic
    public long getTrip_ID() {
        return trip_ID;
    }

    public void setTrip_ID(long trip_ID) {
        this.trip_ID = trip_ID;
    }
}
