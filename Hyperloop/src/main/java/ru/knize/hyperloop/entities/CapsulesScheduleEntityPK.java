package ru.knize.hyperloop.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by knize on 23.08.16.
 */
public class CapsulesScheduleEntityPK implements Serializable {
    private int capsuleId;
    private int stationId;

    @Column(name = "Capsule_ID", nullable = false)
    @Id
    public int getCapsuleId() {
        return capsuleId;
    }

    public void setCapsuleId(int capsuleId) {
        this.capsuleId = capsuleId;
    }

    @Column(name = "Station_ID", nullable = false)
    @Id
    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CapsulesScheduleEntityPK that = (CapsulesScheduleEntityPK) o;

        if (capsuleId != that.capsuleId) return false;
        if (stationId != that.stationId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = capsuleId;
        result = 31 * result + stationId;
        return result;
    }
}
