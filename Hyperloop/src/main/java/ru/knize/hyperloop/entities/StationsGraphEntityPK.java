package ru.knize.hyperloop.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by knize on 28.08.16.
 */
public class StationsGraphEntityPK implements Serializable {
    private int branchIndex;
    private int stationIndex;

    @Column(name = "Branch_Index")
    @Id
    public int getBranchIndex() {
        return branchIndex;
    }

    public void setBranchIndex(int branchIndex) {
        this.branchIndex = branchIndex;
    }

    @Column(name = "Station_Index")
    @Id
    public int getStationIndex() {
        return stationIndex;
    }

    public void setStationIndex(int stationIndex) {
        this.stationIndex = stationIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StationsGraphEntityPK that = (StationsGraphEntityPK) o;

        if (branchIndex != that.branchIndex) return false;
        if (stationIndex != that.stationIndex) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = branchIndex;
        result = 31 * result + stationIndex;
        return result;
    }
}
