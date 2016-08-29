package ru.knize.hyperloop.entities;

import javax.persistence.*;

/**
 * Created by knize on 28.08.16.
 */
@Entity
@Table(name = "Stations_Graph", schema = "Hyperloop", catalog = "")
@IdClass(StationsGraphEntityPK.class)
public class StationsGraphEntity {
    private int branchIndex;
    private int stationIndex;
    private Integer rangeKm;
    private Integer stationId;

    @Id
    @Column(name = "Branch_Index")
    public int getBranchIndex() {
        return branchIndex;
    }

    public void setBranchIndex(int branchIndex) {
        this.branchIndex = branchIndex;
    }

    @Id
    @Column(name = "Station_Index")
    public int getStationIndex() {
        return stationIndex;
    }

    public void setStationIndex(int stationIndex) {
        this.stationIndex = stationIndex;
    }

    @Basic
    @Column(name = "Range_km")
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

        if (branchIndex != that.branchIndex) return false;
        if (stationIndex != that.stationIndex) return false;
        if (rangeKm != null ? !rangeKm.equals(that.rangeKm) : that.rangeKm != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = branchIndex;
        result = 31 * result + stationIndex;
        result = 31 * result + (rangeKm != null ? rangeKm.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "Station_ID")
    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }
}
