package ru.knize.hyperloop.DTO;

import java.util.List;

/**
 * Created by knize on 09.10.16.
 */

public class StationDTOWrapper {
    private List<StationDTOID> create;
    private List<StationDTOID> update;
    private List<Integer> delete;
    private List<EdgeDTO> edges;

    public StationDTOWrapper() {
    }

    @Override
    public String toString() {
        return "StationDTOWrapper{" +
                "create=" + create +
                ", update=" + update +
                ", delete=" + delete +
                ", edges=" + edges +
                '}';
    }

    public List<EdgeDTO> getEdges() {
        return edges;
    }

    public void setEdges(List<EdgeDTO> edges) {
        this.edges = edges;
    }

    public List<StationDTOID> getUpdate() {
        return update;
    }

    public void setUpdate(List<StationDTOID> update) {
        this.update = update;
    }

    public List<Integer> getDelete() {
        return delete;
    }

    public void setDelete(List<Integer> delete) {
        this.delete = delete;
    }

    public List<StationDTOID> getCreate() {
        return create;
    }

    public void setCreate(List<StationDTOID> create) {
        this.create = create;
    }
}
