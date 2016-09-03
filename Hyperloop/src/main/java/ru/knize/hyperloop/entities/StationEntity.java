package ru.knize.hyperloop.entities;

import javax.persistence.*;

/**
 * Created by knize on 28.08.16.
 */
@Entity
@Table(name = "Station", schema = "Hyperloop", catalog = "")
public class StationEntity {
    private int stationId;
    private String stationName;
    private Integer timezoneIndex;
    private Integer timezoneId;

    @Id
    @Column(name = "Station_ID")
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
    @Column(name = "Timezone_Index")
    public Integer getTimezoneIndex() {
        return timezoneIndex;
    }

    public void setTimezoneIndex(Integer timezoneIndex) {
        this.timezoneIndex = timezoneIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StationEntity that = (StationEntity) o;

        if (stationId != that.stationId) return false;
        if (stationName != null ? !stationName.equals(that.stationName) : that.stationName != null) return false;
        if (timezoneIndex != null ? !timezoneIndex.equals(that.timezoneIndex) : that.timezoneIndex != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stationId;
        result = 31 * result + (stationName != null ? stationName.hashCode() : 0);
        result = 31 * result + (timezoneIndex != null ? timezoneIndex.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "Timezone_ID")
    public Integer getTimezoneId() {
        return timezoneId;
    }

    public void setTimezoneId(Integer timezoneId) {
        this.timezoneId = timezoneId;
    }
}
