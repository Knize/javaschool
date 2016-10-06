package ru.knize.hyperloop.services.pathfinder;


import ru.knize.hyperloop.entities.EdgeEntity;
import ru.knize.hyperloop.entities.StationEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class PathFinderService {

    private final List<StationEntity> nodes;
    private final List<EdgeEntity> edges;
    private Set<StationEntity> settledNodes;
    private Set<StationEntity> unSettledNodes;
    private Map<StationEntity, StationEntity> predecessors;
    private Map<StationEntity, Integer> distance;

    public PathFinderService(Graph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<StationEntity>(graph.getVertexes());
        this.edges = new ArrayList<EdgeEntity>(graph.getEdges());
    }

    public void execute(StationEntity source) {
        settledNodes = new HashSet<StationEntity>();
        unSettledNodes = new HashSet<StationEntity>();
        distance = new HashMap<StationEntity, Integer>();
        predecessors = new HashMap<StationEntity, StationEntity>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            StationEntity node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(StationEntity node) {
        List<StationEntity> adjacentNodes = getNeighbors(node);
        for (StationEntity target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(StationEntity node, StationEntity target) {
        for (EdgeEntity edge : edges) {
            if (edge.getFromStation().equals(node)
                    && edge.getToStation().equals(target)) {
                return edge.getRangeKm();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<StationEntity> getNeighbors(StationEntity node) {
        List<StationEntity> neighbors = new ArrayList<StationEntity>();
        for (EdgeEntity edge : edges) {
            if (edge.getFromStation().equals(node)
                    && !isSettled(edge.getToStation())) {
                neighbors.add(edge.getToStation());
            }
        }
        return neighbors;
    }

    private StationEntity getMinimum(Set<StationEntity> vertexes) {
        StationEntity minimum = null;
        for (StationEntity vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(StationEntity vertex) {
        return settledNodes.contains(vertex);
    }

    private int getShortestDistance(StationEntity destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<StationEntity> getPath(StationEntity target) {
        LinkedList<StationEntity> path = new LinkedList<StationEntity>();
        StationEntity step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

}
