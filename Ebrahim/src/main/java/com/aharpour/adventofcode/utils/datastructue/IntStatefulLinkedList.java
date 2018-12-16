package com.aharpour.adventofcode.utils.datastructue;

public class IntStatefulLinkedList {

    private Node first;
    private Node last;
    private Node currentMarble;

    public int remove() {
        int value = currentMarble.item;
        Node prev = currentMarble.prev;
        Node next = currentMarble.next;
        if (prev != null) {
            prev.next = next;
        } else {
            first = next;
        }
        if (next != null) {
            next.prev = prev;
            currentMarble = next;
        } else {
            last = prev;
            currentMarble = first;
        }
        return value;
    }

    private static class Node {
        int item;
        Node next;
        Node prev;

        Node(Node prev, int element, Node next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public void insert(int value) {
        Node next = currentMarble != null ? currentMarble.next : null;
        Node newNode = new Node(currentMarble, value, next);
        if (next != null) {
            next.prev = newNode;
        } else {
            last = newNode;
        }
        if (currentMarble == null) {
            first = newNode;
        } else {
            currentMarble.next = newNode;
        }
        currentMarble = newNode;
    }

    public int forward(int steps) {
        if (steps < 0) {
            throw new IllegalArgumentException();
        }
        for (int s = 0; s < steps; s++) {
            Node next = currentMarble.next;
            if (next != null) {
                currentMarble = next;
            } else {
                currentMarble = first;
            }
        }
        return currentMarble.item;
    }

    public int backward(int steps) {
        if (steps < 0) {
            throw new IllegalArgumentException();
        }
        for (int s = 0; s < steps; s++) {
            Node prev = currentMarble.prev;
            if (prev != null) {
                currentMarble = prev;
            } else {
                currentMarble = last;
            }
        }
        return currentMarble.item;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        Node node = this.first;
        while (node != null) {
            builder.append(node.item).append(", ");
            node = node.next;
        }
        builder.append(']');
        return builder.toString();
    }
}