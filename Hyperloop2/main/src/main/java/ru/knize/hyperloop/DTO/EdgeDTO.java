package ru.knize.hyperloop.DTO;

/**
 * Created by knize on 11.10.16.
 */
public class EdgeDTO {
    private int fromStationId;
    private int toStationId;
    private int branchId;
    private int id;
    private int rangeKm;

    public EdgeDTO() {
    }

    @Override
    public String toString() {
        return "EdgeDTO{" +
                "fromStationId=" + fromStationId +
                ", toStationId=" + toStationId +
                ", branchId=" + branchId +
                ", id=" + id +
                ", rangeKm=" + rangeKm +
                '}';
    }

    public int getFromStationId() {
        return fromStationId;
    }

    public void setFromStationId(int fromStationId) {
        this.fromStationId = fromStationId;
    }

    public void setFromStationId(String fromStationId){
        this.fromStationId = Integer.parseInt(fromStationId);
    }

    public int getToStationId() {
        return toStationId;
    }

    public void setToStationId(int toStationId) {
        this.toStationId = toStationId;
    }

    public void setToStationId(String toStationId){
        this.fromStationId = Integer.parseInt(toStationId);
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public void setBranchId(String branchId){
        this.branchId = Integer.parseInt(branchId);
    }

    public int getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public void setId(int id) {
        this.id = id;
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
