package ru.knize.hyperloop.DTO;

import java.util.List;

/**
 * Created by knize on 12.10.16.
 */
public class StationsEdgesInfo {
    private List<StationDTO> stations;
    private List<EdgeDTO> edges;

    public StationsEdgesInfo() {
    }

    @Override
    public String toString() {
        return "StationsEdgesInfo{" +
                "stations=" + stations +
                ", edges=" + edges +
                '}';
    }

    public List<StationDTO> getStations() {
        return stations;
    }

    public void setStations(List<StationDTO> stations) {
        this.stations = stations;
    }

    public List<EdgeDTO> getEdges() {
        return edges;
    }

    public void setEdges(List<EdgeDTO> edges) {
        this.edges = edges;
    }
}
