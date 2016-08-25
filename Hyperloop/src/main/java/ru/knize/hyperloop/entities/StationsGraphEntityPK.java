package ru.knize.hyperloop.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by knize on 23.08.16.
 */
public class StationsGraphEntityPK implements Serializable {
    private int stationIdOne;
    private int stationIdTwo;

    @Column(name = "Station_ID_One", nullable = false)
    @Id
    public int getStationIdOne() {
        return stationIdOne;
    }

    public void setStationIdOne(int stationIdOne) {
        this.stationIdOne = stationIdOne;
    }

    @Column(name = "Station_ID_Two", nullable = false)
    @Id
    public int getStationIdTwo() {
        return stationIdTwo;
    }

    public void setStationIdTwo(int stationIdTwo) {
        this.stationIdTwo = stationIdTwo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StationsGraphEntityPK that = (StationsGraphEntityPK) o;

        if (stationIdOne != that.stationIdOne) return false;
        if (stationIdTwo != that.stationIdTwo) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stationIdOne;
        result = 31 * result + stationIdTwo;
        return result;
    }
}
