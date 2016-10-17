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
    private StationEntity fromStation;
    private StationEntity toStation;
    private int id;
    private int tripType;
    private long tripID;
    private EdgeEntity edge;

    @ManyToOne
    @JoinColumn(nullable = false)
    public StationEntity getToStation() {
        return toStation;
    }

    public void setToStation(StationEntity toStation) {
        this.toStation = toStation;
    }

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
        if (tripType != that.tripType) return false;
        if (tripID != that.tripID) return false;
        if (!departureTime.equals(that.departureTime)) return false;
        if (!arrivalTime.equals(that.arrivalTime)) return false;
        if (!capsule.equals(that.capsule)) return false;
        if (!fromStation.equals(that.fromStation)) return false;
        if (!toStation.equals(that.toStation)) return false;
        return edge.equals(that.edge);

    }

    @Override
    public int hashCode() {
        int result = departureTime.hashCode();
        result = 31 * result + arrivalTime.hashCode();
        result = 31 * result + capsule.hashCode();
        result = 31 * result + fromStation.hashCode();
        result = 31 * result + toStation.hashCode();
        result = 31 * result + id;
        result = 31 * result + tripType;
        result = 31 * result + (int) (tripID ^ (tripID >>> 32));
        result = 31 * result + edge.hashCode();
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
    public StationEntity getFromStation() {
        return fromStation;
    }

    public void setFromStation(StationEntity stationByStationId) {
        this.fromStation = stationByStationId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int capsuleScheduleId) {
        this.id = capsuleScheduleId;
    }

    @Basic
    public int getTripType() {
        return tripType;
    }

    public void setTripType(int tripType) {
        this.tripType = tripType;
    }

    @Basic
    public long getTripID() {
        return tripID;
    }

    public void setTripID(long trip_ID) {
        this.tripID = trip_ID;
    }

    @ManyToOne
    public EdgeEntity getEdge() {
        return edge;
    }

    public void setEdge(EdgeEntity edge) {
        this.edge = edge;
    }
}
