package ru.knize.hyperloop.entities;

import javax.persistence.*;

/**
 * Created by knize on 23.08.16.
 */
@Entity
@Table(name = "Capsule", schema = "Hyperloop")
public class CapsuleEntity {
    private int capsuleId;
    private Integer seatsNumber;
    private Integer carSlots;

    @Id
    @Column(name = "Capsule_ID", nullable = false)
    public int getCapsuleId() {
        return capsuleId;
    }

    public void setCapsuleId(int capsuleId) {
        this.capsuleId = capsuleId;
    }

    @Basic
    @Column(name = "Seats_Number", nullable = true)
    public Integer getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(Integer seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    @Basic
    @Column(name = "Car_Slots", nullable = true)
    public Integer getCarSlots() {
        return carSlots;
    }

    public void setCarSlots(Integer carSlots) {
        this.carSlots = carSlots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CapsuleEntity that = (CapsuleEntity) o;

        if (capsuleId != that.capsuleId) return false;
        if (seatsNumber != null ? !seatsNumber.equals(that.seatsNumber) : that.seatsNumber != null) return false;
        if (carSlots != null ? !carSlots.equals(that.carSlots) : that.carSlots != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = capsuleId;
        result = 31 * result + (seatsNumber != null ? seatsNumber.hashCode() : 0);
        result = 31 * result + (carSlots != null ? carSlots.hashCode() : 0);
        return result;
    }
}
