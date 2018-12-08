package com.aharpour.adventofcode;


import java.util.ArrayList;
import java.util.Set;

import com.aharpour.adventofcode.utils.graph.DirectionalGraph;
import com.aharpour.adventofcode.utils.worker.Pool;

public class Day7Question2 extends Day7 {

    private final int taskBaseLine;
    private Pool pool;
    private static final int A = 'A';

    public Day7Question2(int taskBaseLine, int numberOfWorkers) {
        this.taskBaseLine = taskBaseLine;
        this.pool = new Pool(numberOfWorkers);
    }

    public int calculate(String string) {
        parseInput(string);
        int i = 0;
        for (final int originalSize = this.nodes.size(); dones.size() < originalSize; i++) {
            for (DirectionalGraph.Node node : new ArrayList<>(nodes)) {
                Set<DirectionalGraph.Node> from = node.getFrom();
                from.removeAll(dones);
                if (from.isEmpty()) {
                    pool.addTask(getTask(node));
                    nodes.remove(node);
                }
            }
            pool.cycle();
        }
        return i;
    }

    private Pool.Task<DirectionalGraph.Node> getTask(DirectionalGraph.Node node) {
        int charAsInt = node.getName().chars().findFirst().getAsInt();
        return pool.new Task<>(taskBaseLine + charAsInt - A + 1, node, n -> dones.add(n));
    }


}