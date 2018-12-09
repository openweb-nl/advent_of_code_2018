package com.aharpour.adventofcode.utils.datastructue;

public class StatefulLinkedList<T> {

    private Node<T> first;
    private Node<T> last;
    private Node<T> currentMarble;

    public T remove() {
        T value = currentMarble.item;
        Node<T> prev = currentMarble.prev;
        Node<T> next = currentMarble.next;
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

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T element, Node<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public void insert(T value) {
        Node<T> next = currentMarble != null ? currentMarble.next : null;
        Node<T> newNode = new Node<>(currentMarble, value, next);
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

    public T forward(int steps) {
        if (steps < 0) {
            throw new IllegalArgumentException();
        }
        for (int s = 0; s < steps; s++) {
            Node<T> next = currentMarble.next;
            if (next != null) {
                currentMarble = next;
            } else {
                currentMarble = first;
            }
        }
        return currentMarble.item;
    }

    public T backward(int steps) {
        if (steps < 0) {
            throw new IllegalArgumentException();
        }
        for (int s = 0; s < steps; s++) {
            Node<T> prev = currentMarble.prev;
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
