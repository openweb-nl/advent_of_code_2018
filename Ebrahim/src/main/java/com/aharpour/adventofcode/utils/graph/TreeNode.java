package com.aharpour.adventofcode.utils.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ebrahim Aharpour
 * @since 12/8/2018
 */

public class TreeNode<T> {
    private T metadata;
    private List<TreeNode<T>> children = new ArrayList<>();


    public void addChild(TreeNode<T> node) {
        children.add(node);
    }

    public List<TreeNode<T>> getChildren() {
        return new ArrayList<>(children);
    }

    public T getMetadata() {
        return metadata;
    }

    public void setMetadata(T metadata) {
        this.metadata = metadata;
    }

    public List<TreeNode<T>> getDescendants() {
        return getDescendants(this);

    }

    private List<TreeNode<T>> getDescendants(TreeNode<T> node) {
        ArrayList<TreeNode<T>> result = new ArrayList<>();
        if (node.children.isEmpty()) {
            result.add(node);
        } else {
            for (TreeNode<T> child : node.children) {
                result.addAll(getDescendants(child));
            }
            result.add(node);
        }
        return result;
    }
}
