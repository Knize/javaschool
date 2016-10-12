package ru.knize.hyperloop.DTO;

import ru.knize.hyperloop.entities.StationEntity;

/**
 * Created by knize on 11.10.16.
 */
public class EdgeDTO {
    private StationDTOID fromStation;
    private StationDTOID toStation;
    private BranchDTO branch;
    private int id;
    private int rangeKm;

    public EdgeDTO() {
    }

    public StationDTOID getFromStation() {
        return fromStation;
    }

    public void setFromStation(StationDTOID fromStation) {
        this.fromStation = fromStation;
    }

    public StationDTOID getToStation() {
        return toStation;
    }

    public void setToStation(StationDTOID toStation) {
        this.toStation = toStation;
    }

    public BranchDTO getBranch() {
        return branch;
    }

    public void setBranch(BranchDTO branch) {
        this.branch = branch;
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
