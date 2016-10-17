package ru.knize.hyperloop.entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by knize on 03.09.16.
 */
@Entity
@Table(name = "Station", schema = "Hyperloop", catalog = "")
public class StationEntity {
    private int id;
    private String name;
    private String timezone;
    private Collection<CapsulesScheduleEntity> capsulesSchedules;
    private Collection<TicketEntity> tickets;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int stationId) {
        this.id = stationId;
    }

    @Basic

    public String getName() {
        return name;
    }

    public void setName(String stationName) {
        this.name = stationName;
    }

    @Basic

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

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (timezone != null ? !timezone.equals(that.timezone) : that.timezone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (timezone != null ? timezone.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "fromStation")
    public Collection<CapsulesScheduleEntity> getCapsulesSchedules() {
        return capsulesSchedules;
    }

    public void setCapsulesSchedules(Collection<CapsulesScheduleEntity> capsulesSchedulesByStationId) {
        this.capsulesSchedules = capsulesSchedulesByStationId;
    }

    @OneToMany(mappedBy = "fromStation")
    public Collection<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<TicketEntity> ticketsByStationId) {
        this.tickets = ticketsByStationId;
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

    private int endForBranch;

    @Basic
    public int getEndForBranch() {
        return endForBranch;
    }

    public void setEndForBranch(int isAnd) {
        this.endForBranch = isAnd;
    }
}
