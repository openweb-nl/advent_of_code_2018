package com.aharpour.adventofcode;


import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day9Question1 {


    private static final Pattern PATTERN = Pattern.compile("(\\d+) players; last marble is worth (\\d+) points");
    private int lastMarbleWorth;
    private long[] scores;

    public long calculate(String string) {
        parseInput(string);
        MarbleGame marbleGame = new MarbleGame(lastMarbleWorth);
        for (int i = 0; i < lastMarbleWorth; i++) {
            scores[i % scores.length] = scores[i % scores.length] + marbleGame.placeMarble();
        }
        return Arrays.stream(scores).max().orElseThrow(IllegalArgumentException::new);
    }

    private void parseInput(String string) {
        Matcher matcher = PATTERN.matcher(string);
        if (matcher.find()) {
            scores = new long[Integer.parseInt(matcher.group(1))];
            lastMarbleWorth = Integer.parseInt(matcher.group(2));
        } else {
            throw new IllegalArgumentException();
        }

    }

    class MarbleGame {
        private Node first;
        private Node last;
        private int marble = 1;
        private Node currentMarble;


        MarbleGame(int numberOfMarbles) {
            first = new Node(null, 0, null);
            currentMarble = first;
            last = first;
        }

        int placeMarble() {
            int result = 0;
            if (marble % 23 != 0) {
                forward(1);
                insert(marble);
            } else {
                backward(7);
                result = marble + remove();
            }
            marble++;
            return result;
        }

        private int remove() {
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


        private void insert(int value) {
            Node next = currentMarble.next;
            Node newNode = new Node(currentMarble, value, next);
            if (next != null) {
                next.prev = newNode;
            } else {
                last = newNode;
            }
            currentMarble.next = newNode;
            currentMarble = newNode;
        }

        private Node forward(int steps) {
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
            return currentMarble;
        }

        private Node backward(int steps) {
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
            return currentMarble;
        }


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
}