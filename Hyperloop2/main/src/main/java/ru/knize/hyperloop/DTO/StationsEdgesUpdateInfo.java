package ru.knize.hyperloop.DTO;

import java.util.List;

/**
 * Created by knize on 09.10.16.
 */

public class StationsEdgesUpdateInfo {
    private List<StationDTO> stationsCreate;
    private List<StationDTO> stationsUpdate;
    private List<Integer> stationsDelete;
    private List<EdgeDTO> edgesCreate;
    private List<EdgeDTO> edgesUpdate;
    private List<Integer> edgesDelete;


    public List<EdgeDTO> getEdgesUpdate() {
        return edgesUpdate;
    }

    public void setEdgesUpdate(List<EdgeDTO> edgesUpdate) {
        this.edgesUpdate = edgesUpdate;
    }

    public List<Integer> getEdgesDelete() {
        return edgesDelete;
    }

    public void setEdgesDelete(List<Integer> edgesDelete) {
        this.edgesDelete = edgesDelete;
    }

    public StationsEdgesUpdateInfo() {
    }

    @Override
    public String toString() {
        return "StationsEdgesUpdateInfo{" +
                "stationsCreate=" + stationsCreate +
                ", stationsUpdate=" + stationsUpdate +
                ", stationsDelete=" + stationsDelete +
                ", edgesCreate=" + edgesCreate +
                ", edgesUpdate=" + edgesUpdate +
                ", edgesDelete=" + edgesDelete +
                '}';
    }

    public List<EdgeDTO> getEdgesCreate() {
        return edgesCreate;
    }

    public void setEdgesCreate(List<EdgeDTO> edgesCreate) {
        this.edgesCreate = edgesCreate;
    }

    public List<StationDTO> getStationsUpdate() {
        return stationsUpdate;
    }

    public void setStationsUpdate(List<StationDTO> stationsUpdate) {
        this.stationsUpdate = stationsUpdate;
    }

    public List<Integer> getStationsDelete() {
        return stationsDelete;
    }

    public void setStationsDelete(List<Integer> stationsDelete) {
        this.stationsDelete = stationsDelete;
    }

    public List<StationDTO> getStationsCreate() {
        return stationsCreate;
    }

    public void setStationsCreate(List<StationDTO> stationsCreate) {
        this.stationsCreate = stationsCreate;
    }
}
