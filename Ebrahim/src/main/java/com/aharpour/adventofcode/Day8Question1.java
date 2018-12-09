package com.aharpour.adventofcode;


import java.util.Arrays;

import com.aharpour.adventofcode.utils.graph.TreeNode;

public class Day8Question1 extends Day8 {


    public int calculate(String string) {
        parseInput(string);
        return tree.getDescendants().stream()
                .map(TreeNode::getMetadata)
                .flatMapToInt(Arrays::stream)
                .sum();
    }
}