package ru.knize.hyperloop.DTO;

/**
 * Created by knize on 04.10.16.
 */
public class StationDTO {
    private String name;
    private String timezone;
    private int rangeKm;
    private int latitude;
    private int longtitude;

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public void setLatitude(String latitudeStr) {
        this.latitude = Integer.parseInt(latitudeStr);
    }

    public int getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(int longtitude) {
        this.longtitude = longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = Integer.parseInt(longtitude);
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

    public void setRangeKm(String rangeKmStr) {
        try {
            this.rangeKm = Integer.parseInt(rangeKmStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void setRangeKm(int rangeKm) {
        this.rangeKm = rangeKm;
    }
}
