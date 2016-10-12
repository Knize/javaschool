package ru.knize.hyperloop.DTO;

/**
 * Created by knize on 04.10.16.
 */
public class StationDTO {
    private String name;
    private String timezone;
    private int rangeKm;
    private double latitude;
    private double longitude;

    public StationDTO() { }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitudeStr) {
        try {
            this.latitude = Integer.parseInt(latitudeStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        try {
            this.longitude = Integer.parseInt(longitude);
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

    public int getRangeKm() {
        return rangeKm;
    }

    public void setRangeKm(int rangeKm) {
        this.rangeKm = rangeKm;
    }

    public void setRangeKm(String rangeKmStr) {
        try {
            this.rangeKm = Integer.parseInt(rangeKmStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }



    @Override
    public String toString() {
        return "StationDTO{" +
                "name='" + name + '\'' +
                ", timezone='" + timezone + '\'' +
                ", rangeKm=" + rangeKm +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
