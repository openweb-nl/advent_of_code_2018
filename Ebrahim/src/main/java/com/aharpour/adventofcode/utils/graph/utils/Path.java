package com.aharpour.adventofcode.utils.graph.utils;

import com.aharpour.adventofcode.utils.graph.AbstractGraph;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class Path implements Cloneable{
    @Getter
    private List<AbstractGraph.Edge> edges = new ArrayList<>();

    @Getter
    private int length = 0;

    Path() {
    }

    Path addSegment(AbstractGraph.Edge edge) {
        if (!edges.isEmpty() && edges.get(edges.size() - 1).getTo() != edge.getFrom()) {
            throw new IllegalArgumentException();
        }
        edges.add(edge);
        this.length +=  edge.getWeight();
        return this;
    }

    @SneakyThrows
    public Path deepClone() {
        Path clone = (Path) super.clone();
        clone.edges = new ArrayList<>(this.edges);
        return clone;
    }
}
