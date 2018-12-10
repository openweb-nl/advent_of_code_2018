package com.gklijs.adventofcode.day8;

import java.util.ArrayList;
import java.util.List;

class Node {

    final int childCount;
    final int metaCount;
    final List<Node> childNodes;
    final List<Integer> metaData;
    private int buffer;

    Node(int childCount, int metaCount) {
        this.childCount = childCount;
        this.metaCount = metaCount;
        this.childNodes = new ArrayList<>(childCount);
        this.metaData = new ArrayList<>(metaCount);
        this.buffer = -1;
    }

    boolean add(int addition) {
        if (!childNodes.isEmpty() && childNodes.get(childNodes.size() - 1).add(addition)) {
            return true;
        }
        if (childNodes.size() < childCount) {
            if (buffer == -1) {
                buffer = addition;
            } else {
                childNodes.add(new Node(buffer, addition));
                buffer = -1;
            }
            return true;
        }
        if (metaData.size() < metaCount) {
            metaData.add(addition);
            return true;
        } else {
            return false;
        }
    }
}
