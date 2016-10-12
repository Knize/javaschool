package ru.knize.hyperloop.DTO;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by knize on 09.10.16.
 */

public class StationDTOWrapper {
    private List<StationDTOID> stationsCreate;
    private List<StationDTOID> stationsUpdate;
    private List<Integer> stationsDelete;
    private List<EdgeDTO> edgesCreate;
    private List<EdgeDTO> edgesUpgrade;
    private List<Integer> edgesDelete;


    public List<EdgeDTO> getEdgesUpgrade() {
        return edgesUpgrade;
    }

    public void setEdgesUpgrade(List<EdgeDTO> edgesUpgrade) {
        this.edgesUpgrade = edgesUpgrade;
    }

    public List<Integer> getEdgesDelete() {
        return edgesDelete;
    }

    public void setEdgesDelete(List<Integer> edgesDelete) {
        this.edgesDelete = edgesDelete;
    }

    public StationDTOWrapper() {
    }

    @Override
    public String toString() {
        return "StationDTOWrapper{" +
                "stationsCreate=" + stationsCreate +
                ", stationsUpdate=" + stationsUpdate +
                ", stationsDelete=" + stationsDelete +
                ", edgesCreate=" + edgesCreate +
                ", edgesUpgrade=" + edgesUpgrade +
                ", edgesDelete=" + edgesDelete +
                '}';
    }

    public List<EdgeDTO> getEdgesCreate() {
        return edgesCreate;
    }

    public void setEdgesCreate(List<EdgeDTO> edgesCreate) {
        this.edgesCreate = edgesCreate;
    }

    public List<StationDTOID> getStationsUpdate() {
        return stationsUpdate;
    }

    public void setStationsUpdate(List<StationDTOID> stationsUpdate) {
        this.stationsUpdate = stationsUpdate;
    }

    public List<Integer> getStationsDelete() {
        return stationsDelete;
    }

    public void setStationsDelete(List<Integer> stationsDelete) {
        this.stationsDelete = stationsDelete;
    }

    public List<StationDTOID> getStationsCreate() {
        return stationsCreate;
    }

    public void setStationsCreate(List<StationDTOID> stationsCreate) {
        this.stationsCreate = stationsCreate;
    }
}
