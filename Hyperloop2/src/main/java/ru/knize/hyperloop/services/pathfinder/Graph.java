package ru.knize.hyperloop.services.pathfinder;

/**
 * Created by knize on 06.10.16.
 */

import ru.knize.hyperloop.entities.EdgeEntity;
import ru.knize.hyperloop.entities.StationEntity;

import java.util.List;

public class Graph {
    private final List<StationEntity> vertexes;
    private final List<EdgeEntity> edges;

    public Graph(List<StationEntity> vertexes, List<EdgeEntity> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    public List<StationEntity> getVertexes() {
        return vertexes;
    }

    public List<EdgeEntity> getEdges() {
        return edges;
    }



}