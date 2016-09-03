package ru.knize.hyperloop.entities;

import javax.persistence.*;

/**
 * Created by knize on 03.09.16.
 */
@Entity
@Table(name = "Timezone", schema = "Hyperloop", catalog = "")
public class TimezoneEntity {
    private int timezoneId;
    private String timezoneName;

    @Id
    @Column(name = "Timezone_ID")
    public int getTimezoneId() {
        return timezoneId;
    }

    public void setTimezoneId(int timezoneId) {
        this.timezoneId = timezoneId;
    }

    @Basic
    @Column(name = "Timezone_Name")
    public String getTimezoneName() {
        return timezoneName;
    }

    public void setTimezoneName(String timezoneName) {
        this.timezoneName = timezoneName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimezoneEntity that = (TimezoneEntity) o;

        if (timezoneId != that.timezoneId) return false;
        if (timezoneName != null ? !timezoneName.equals(that.timezoneName) : that.timezoneName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = timezoneId;
        result = 31 * result + (timezoneName != null ? timezoneName.hashCode() : 0);
        return result;
    }
}
