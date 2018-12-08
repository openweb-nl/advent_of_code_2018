package com.aharpour.adventofcode;

import com.aharpour.adventofcode.utils.graph.TreeNode;


import static com.aharpour.adventofcode.utils.StringUtils.stringToIntArray;

public class Day8 {

    protected TreeNode<int[]> tree;
    private int[] array;
    private int i = 0;

    protected void parseInput(String string) {
        array = stringToIntArray(string, "\\s+");
        tree = recursiveRead();
    }

    private TreeNode<int[]> recursiveRead() {
        int[] graphData = read(2);
        TreeNode<int[]> result = new TreeNode<>();
        for (int j = 0; j < graphData[0]; j++) {
            result.addChild(recursiveRead());
        }
        result.setMetadata(read(graphData[1]));
        return result;

    }

    private int[] read(int numberOfItems) {
        int[] result = new int[numberOfItems];
        System.arraycopy(array, i, result, 0, result.length);
        i = i + numberOfItems;
        return result;
    }


}
