package ru.knize.hyperloop.DTO;

import ru.knize.hyperloop.entities.StationEntity;

/**
 * Created by knize on 11.10.16.
 */
public class EdgeDTO {
    private int fromStationId;
    private int toStationId;
    private int rangeKm;
    private int branch;

    public EdgeDTO() {
    }

    public int getFromStationId() {
        return fromStationId;
    }

    public void setFromStationId(String fromStationId) {
        this.fromStationId = Integer.parseInt(fromStationId);
    }

    public int getToStationId() {
        return toStationId;
    }

    public void setToStationId(String toStationId) {
        this.toStationId = Integer.parseInt(toStationId);
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

    public int getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = Integer.parseInt(branch);
    }

    @Override
    public String toString() {
        return "EdgeDTO{" +
                "fromStationId=" + fromStationId +
                ", toStationId=" + toStationId +
                ", rangeKm=" + rangeKm +
                ", branch=" + branch +
                '}';
    }
}
