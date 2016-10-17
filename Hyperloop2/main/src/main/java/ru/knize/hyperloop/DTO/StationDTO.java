package ru.knize.hyperloop.DTO;


import ru.knize.hyperloop.entities.StationEntity;

/**
 * Created by knize on 06.10.16.
 */
public class StationDTO {
    private int id;
    private String name;
    private String timezone;
    private double latitude;
    private double longitude;
    private int endForBranch;

    public StationDTO() {
    }

    public static StationDTO fromStationEntity(StationEntity se) {
        StationDTO stationDTO = new StationDTO();
        stationDTO.setId(Integer.toString(se.getId()));
        stationDTO.setName(se.getName());
        stationDTO.setTimezone(se.getTimezone());
        stationDTO.setLatitude(Double.toString(se.getLatitude()));
        stationDTO.setLongitude(Double.toString(se.getLongitude()));

        return stationDTO;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getEndForBranch() {
        return endForBranch;
    }

    public void setEndForBranch(int endForBranch) {
        this.endForBranch = endForBranch;
    }

    public void setEndForBranch(String endForBranch) {
        this.endForBranch = Integer.parseInt(endForBranch);
    }

    @Override
    public String toString() {
        return "StationDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", timezone='" + timezone + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", endForBranch=" + endForBranch +
                '}';
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitudeStr) {
        try {
            this.latitude = Double.parseDouble(latitudeStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        try {
            this.longitude = Double.parseDouble(longitude);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public int getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }


}
