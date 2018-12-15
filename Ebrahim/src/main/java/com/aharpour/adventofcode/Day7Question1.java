package com.aharpour.adventofcode;


import com.aharpour.adventofcode.utils.graph.DirectionalGraph;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

public class Day7Question1 extends Day7 {


    public String calculate(String string) {
        parseInput(string);
        int previousSize;
        do {
            previousSize = nodes.size();

            for (Iterator<DirectionalGraph.Node> iterator = nodes.iterator(); iterator.hasNext(); ) {
                DirectionalGraph.Node node = iterator.next();
                Set<DirectionalGraph.Node> from = node.getFromNodes();
                from.removeAll(dones);
                if (from.isEmpty()) {
                    dones.add(node);
                    nodes.remove(node);
                    break;
                }
            }
        } while (nodes.size() > 0 && previousSize > nodes.size());

        return dones.stream().map(DirectionalGraph.Node::getName).collect(Collectors.joining());
    }
}