package ru.knize.hyperloop.entities;

import javax.persistence.*;

/**
 * Created by knize on 23.08.16.
 */
@Entity
@Table(name = "Stations_Graph", schema = "Hyperloop")
@IdClass(StationsGraphEntityPK.class)
public class StationsGraphEntity {
    private int stationIdOne;
    private int stationIdTwo;
    private Integer rangeKm;

    @Id
    @Column(name = "Station_ID_One", nullable = false)
    public int getStationIdOne() {
        return stationIdOne;
    }

    public void setStationIdOne(int stationIdOne) {
        this.stationIdOne = stationIdOne;
    }

    @Id
    @Column(name = "Station_ID_Two", nullable = false)
    public int getStationIdTwo() {
        return stationIdTwo;
    }

    public void setStationIdTwo(int stationIdTwo) {
        this.stationIdTwo = stationIdTwo;
    }

    @Basic
    @Column(name = "Range_km", nullable = true)
    public Integer getRangeKm() {
        return rangeKm;
    }

    public void setRangeKm(Integer rangeKm) {
        this.rangeKm = rangeKm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StationsGraphEntity that = (StationsGraphEntity) o;

        if (stationIdOne != that.stationIdOne) return false;
        if (stationIdTwo != that.stationIdTwo) return false;
        if (rangeKm != null ? !rangeKm.equals(that.rangeKm) : that.rangeKm != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stationIdOne;
        result = 31 * result + stationIdTwo;
        result = 31 * result + (rangeKm != null ? rangeKm.hashCode() : 0);
        return result;
    }
}
