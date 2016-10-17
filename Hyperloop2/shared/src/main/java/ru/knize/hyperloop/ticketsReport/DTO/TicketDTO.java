package ru.knize.hyperloop.ticketsReport.DTO;


/**
 * Created by knize on 15.10.16.
 */
public class TicketDTO {
    private int id;
    private boolean carSlot;
    private double price;
    private long tripId;
    private int accountId;
    private int capsuleId;
    private int fromStationId;
    private int toStationId;
    private int personId;

    public TicketDTO() {
    }

    @Override
    public String toString() {
        return "TicketDTO{" +
                "id=" + id +
                ", carSlot=" + carSlot +
                ", price=" + price +
                ", tripId=" + tripId +
                ", accountId=" + accountId +
                ", capsuleId=" + capsuleId +
                ", fromStationId=" + fromStationId +
                ", toStationId=" + toStationId +
                ", personId=" + personId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCarSlot() {
        return carSlot;
    }

    public void setCarSlot(boolean carSlot) {
        this.carSlot = carSlot;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getTripId() {
        return tripId;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCapsuleId() {
        return capsuleId;
    }

    public void setCapsuleId(int capsuleId) {
        this.capsuleId = capsuleId;
    }

    public int getFromStationId() {
        return fromStationId;
    }

    public void setFromStationId(int fromStationId) {
        this.fromStationId = fromStationId;
    }

    public int getToStationId() {
        return toStationId;
    }

    public void setToStationId(int toStationId) {
        this.toStationId = toStationId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
