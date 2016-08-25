package ru.knize.hyperloop.entities;

import javax.persistence.*;

/**
 * Created by knize on 23.08.16.
 */
@Entity
@Table(name = "Ticket", schema = "Hyperloop", catalog = "")
public class TicketEntity {
    private int capsuleId;
    private Byte carSlot;

    @Id
    @Column(name = "Capsule_ID", nullable = false)
    public int getCapsuleId() {
        return capsuleId;
    }

    public void setCapsuleId(int capsuleId) {
        this.capsuleId = capsuleId;
    }

    @Basic
    @Column(name = "Car_Slot", nullable = true)
    public Byte getCarSlot() {
        return carSlot;
    }

    public void setCarSlot(Byte carSlot) {
        this.carSlot = carSlot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketEntity that = (TicketEntity) o;

        if (capsuleId != that.capsuleId) return false;
        if (carSlot != null ? !carSlot.equals(that.carSlot) : that.carSlot != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = capsuleId;
        result = 31 * result + (carSlot != null ? carSlot.hashCode() : 0);
        return result;
    }
}
