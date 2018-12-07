package com.aharpour.adventofcode;


import java.util.*;

import com.aharpour.adventofcode.utils.geometry.TwoDPoint;

public class Day6Question1 extends Day6 {

    private Set<Integer> blackList = new HashSet<>();
    private Map<Integer, Integer> pointToArea = new HashMap<>();

    public int calculate(String string) {
        readInput(string);
        populatePointToArea();
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                boolean onEdge = onEdge(x, y);
                getClosestPoint(x, y).ifPresent(i -> {
                    pointToArea.put(i, pointToArea.get(i) + 1);
                    if (onEdge) {
                        blackList.add(i);
                    }
                });
            }
        }
        return getLargestArea();
    }

    private int getLargestArea() {
        return pointToArea.entrySet().stream()
                .filter(i -> !blackList.contains(i.getKey()))
                .mapToInt(Map.Entry::getValue)
                .max().getAsInt();
    }

    private void populatePointToArea() {
        for (int i = 0; i < points.length; i++) {
            pointToArea.put(i, 0);
        }
    }

    private Optional<Integer> getClosestPoint(int x, int y) {
        int minDistance = Integer.MAX_VALUE;
        int index = -1;
        boolean equals = false;
        for (int i = 0; i < points.length; i++) {
            TwoDPoint point = points[i];
            int d = point.manhattanDistance(x, y);
            if (d < minDistance) {
                minDistance = d;
                index = i;
                equals = false;
            } else if (d == minDistance) {
                equals = true;
            }
        }
        return equals ? Optional.empty() : Optional.of(index);
    }

    private boolean onEdge(int x, int y) {
        boolean result = false;
        if (x == minX || x == maxX || y == minY || y == maxY) {
            result = true;
        }
        return result;
    }
}