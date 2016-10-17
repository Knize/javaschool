package ru.knize.hyperloop.DTO;

/**
 * Created by knize on 04.10.16.
 */

public class CapsuleScheduleDTO {
    private int id;
    private int fromStationId;
    private int toStationId;
    private String departureTime;
    private String arrivalTime;
    private boolean direction;
    private long tripId;
    private int capsuleId;
    private int tripType;
    private int edgeId;

    public CapsuleScheduleDTO() {
    }

    public int getToStationId() {
        return toStationId;
    }

    public void setToStationId(int toStationId) {
        this.toStationId = toStationId;
    }

    @Override
    public String toString() {
        return "CapsuleScheduleDTO{" +
                "id=" + id +
                ", fromStationId=" + fromStationId +
                ", toStationId=" + toStationId +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", direction=" + direction +
                ", tripId=" + tripId +
                ", capsuleId=" + capsuleId +
                ", tripType=" + tripType +
                ", edgeId=" + edgeId +
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

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public boolean isDirection() {
        return direction;
    }


    public long getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = Long.parseLong(tripId);
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    public int getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = Integer.parseInt(tripType);
    }

    public void setTripType(int tripType) {
        this.tripType = tripType;
    }

    public int getEdgeId() {
        return edgeId;
    }

    public void setEdgeId(String edgeId) {
        this.edgeId = Integer.parseInt(edgeId);
    }

    public void setEdgeId(int edgeId) {
        this.edgeId = edgeId;
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

    public void setStationId(String stationId) {
        this.fromStationId = Integer.parseInt(stationId);
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public boolean getDirection() {
        return direction;
    }

    public void setDirection(Byte direction) {
        this.direction = direction == 1;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public void setDirection(String direction) {
        this.direction = Boolean.parseBoolean(direction);
    }

}
