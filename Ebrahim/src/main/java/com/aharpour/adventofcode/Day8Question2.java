package com.aharpour.adventofcode;


import java.util.Arrays;
import java.util.List;

import com.aharpour.adventofcode.utils.graph.TreeNode;

public class Day8Question2 extends Day8 {


    public int calculate(String string) {
        parseInput(string);
        return value(tree);
    }

    private int value(TreeNode<int[]> node) {
        List<TreeNode<int[]>> children = node.getChildren();
        if (children.isEmpty()) {
            return Arrays.stream(node.getMetadata()).sum();
        } else {
            return Arrays.stream(node.getMetadata())
                    .filter(i -> 0 < i && i <= children.size())
                    .map(i -> value(children.get(i - 1)))
                    .sum();
        }
    }
}