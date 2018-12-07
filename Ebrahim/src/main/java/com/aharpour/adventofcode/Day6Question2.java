package com.aharpour.adventofcode;


import com.aharpour.adventofcode.utils.geometry.TwoDPoint;

public class Day6Question2 extends Day6 {

    private final int totalDistnace;

    public Day6Question2(int totalDistnace) {
        this.totalDistnace = totalDistnace;
    }

    public int calculate(String string) {
        readInput(string);
        int totalArea = 0;
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                if (isInArea(x, y)) {
                    totalArea++;
                }
            }
        }
        return totalArea;
    }

    private boolean isInArea(int x, int y) {
        int totalDistance = 0;
        for (TwoDPoint point : points) {
            totalDistance += point.manhattanDistance(x, y);
        }
        return totalDistance < this.totalDistnace;
    }
}