package ru.knize.hyperloop.DTO;

/**
 * Created by knize on 04.10.16.
 */
public class StationDTO {
    private String stationIndex;
    private String stationName;
    private String timezone;
    private String rangeKm;
    private String branch;

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getStationIndex() {
        return stationIndex;
    }

    public void setStationIndex(String stationIndex) {
        this.stationIndex = stationIndex;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getRangeKm() {
        return rangeKm;
    }

    public void setRangeKm(String rangeKm) {
        this.rangeKm = rangeKm;
    }
}
