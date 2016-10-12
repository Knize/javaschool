package ru.knize.hyperloop.DTO;

import java.util.List;

/**
 * Created by knize on 12.10.16.
 */
public class StationEdgeDTOWrapper {
    private List<StationDTOID> stations;
    private List<EdgeDTO> edges;

    public StationEdgeDTOWrapper() {
    }

    @Override
    public String toString() {
        return "StationEdgeDTOWrapper{" +
                "stations=" + stations +
                ", edges=" + edges +
                '}';
    }

    public List<StationDTOID> getStations() {
        return stations;
    }

    public void setStations(List<StationDTOID> stations) {
        this.stations = stations;
    }

    public List<EdgeDTO> getEdges() {
        return edges;
    }

    public void setEdges(List<EdgeDTO> edges) {
        this.edges = edges;
    }
}
