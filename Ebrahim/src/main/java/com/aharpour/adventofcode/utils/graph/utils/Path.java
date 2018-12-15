package com.aharpour.adventofcode.utils.graph.utils;

import com.aharpour.adventofcode.utils.graph.AbstractGraph;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.LinkedList;

public class Path implements Cloneable{
    @Getter
    private LinkedList<AbstractGraph.Edge> edges = new LinkedList<>();

    @Getter
    private int length = 0;

    Path() {
    }

    Path addSegment(AbstractGraph.Edge edge) {
        if (!edges.isEmpty() && edges.getLast().getTo() != edge.getFrom()) {
            throw new IllegalArgumentException();
        }
        edges.add(edge);
        this.length +=  edge.getWeight();
        return this;
    }

    @SneakyThrows
    public Path deepClone() {
        Path clone = (Path) super.clone();
        clone.edges = (LinkedList<AbstractGraph.Edge>) this.edges.clone();
        return clone;
    }
}
