package ru.knize.hyperloop.DTO;


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

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public boolean isCarSlot() {
        return carSlot;
    }

    public void setCarSlot(boolean carSlot) {
        this.carSlot = carSlot;
    }

    public void setCarSlot(Byte carSlot) {
        this.carSlot = carSlot == 1;
    }

    public void setCarSlot(String carSlot) {
        this.carSlot = Boolean.parseBoolean(carSlot);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPrice(String price) {
        this.price = Double.parseDouble(price);
    }

    public long getTripId() {
        return tripId;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = Long.parseLong(tripId);
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = Integer.parseInt(accountId);
    }

    public int getCapsuleId() {
        return capsuleId;
    }

    public void setCapsuleId(int capsuleId) {
        this.capsuleId = capsuleId;
    }

    public void setCapsuleId(String capsuleId) {
        this.capsuleId = Integer.parseInt(capsuleId);
    }

    public int getFromStationId() {
        return fromStationId;
    }

    public void setFromStationId(int fromStationId) {
        this.fromStationId = fromStationId;
    }

    public void setFromStationId(String fromStationId) {
        this.fromStationId = Integer.parseInt(fromStationId);
    }

    public int getToStationId() {
        return toStationId;
    }

    public void setToStationId(int toStationId) {
        this.toStationId = toStationId;
    }

    public void setToStationId(String toStationId) {
        this.toStationId = Integer.parseInt(toStationId);
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setPersonId(String personId) {
        this.personId = Integer.parseInt(personId);
    }
}
