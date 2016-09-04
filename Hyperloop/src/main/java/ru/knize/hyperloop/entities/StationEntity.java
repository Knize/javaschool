package ru.knize.hyperloop.entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by knize on 03.09.16.
 */
@Entity
@Table(name = "Station", schema = "Hyperloop", catalog = "")
public class StationEntity {
    private int stationId;
    private String stationName;
    private String timezone;
    private Collection<CapsulesScheduleEntity> capsulesSchedulesByStationId;
    private Collection<TicketEntity> ticketsByStationId;
    private Collection<TicketEntity> ticketsByStationId_0;

    @Id
    @Column(name = "Station_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    @Basic
    @Column(name = "Station_Name")
    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @Basic
    @Column(name = "Timezone")
    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StationEntity that = (StationEntity) o;

        if (stationId != that.stationId) return false;
        if (stationName != null ? !stationName.equals(that.stationName) : that.stationName != null) return false;
        if (timezone != null ? !timezone.equals(that.timezone) : that.timezone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stationId;
        result = 31 * result + (stationName != null ? stationName.hashCode() : 0);
        result = 31 * result + (timezone != null ? timezone.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "stationByStationId")
    public Collection<CapsulesScheduleEntity> getCapsulesSchedulesByStationId() {
        return capsulesSchedulesByStationId;
    }

    public void setCapsulesSchedulesByStationId(Collection<CapsulesScheduleEntity> capsulesSchedulesByStationId) {
        this.capsulesSchedulesByStationId = capsulesSchedulesByStationId;
    }

    @OneToMany(mappedBy = "stationByDepartureStationId")
    public Collection<TicketEntity> getTicketsByStationId() {
        return ticketsByStationId;
    }

    public void setTicketsByStationId(Collection<TicketEntity> ticketsByStationId) {
        this.ticketsByStationId = ticketsByStationId;
    }

    @OneToMany(mappedBy = "stationByArrivalStationId")
    public Collection<TicketEntity> getTicketsByStationId_0() {
        return ticketsByStationId_0;
    }

    public void setTicketsByStationId_0(Collection<TicketEntity> ticketsByStationId_0) {
        this.ticketsByStationId_0 = ticketsByStationId_0;
    }

    private int stationIndex;

    @Basic
    public int getStationIndex() {
        return stationIndex;
    }

    public void setStationIndex(int stationIndex) {
        this.stationIndex = stationIndex;
    }

    private int rangeKm;

    @Basic
    public int getRangeKm() {
        return rangeKm;
    }

    public void setRangeKm(int rangeKm) {
        this.rangeKm = rangeKm;
    }

    private double Latitude;

    @Basic
    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    private double Longitude;

    @Basic
    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    private BranchEntity branch;

    @ManyToOne
    public BranchEntity getBranch() {
        return branch;
    }

    public void setBranch(BranchEntity branchById) {
        this.branch = branchById;
    }
}
