package com.aharpour.adventofcode;

import com.aharpour.adventofcode.utils.geometry.ThreeDPoint;

import java.util.ArrayList;
import java.util.List;

public class Day23Question2 extends Day23 {

    public static final ThreeDPoint ORIGIN = new ThreeDPoint(0, 0, 0);

    public Day23Question2(String input) {
        super(input);
    }

    public int calculate() {
        ThreeDPoint roughLocation = getRoughLocation();
        ThreeDPoint bestPoint = getBestPoint(roughLocation);
        return bestPoint.manhattanDistance(ORIGIN);
    }

    private ThreeDPoint getBestPoint(ThreeDPoint roughLocation) {
        ThreeDPoint currentPoint;
        ThreeDPoint bestPoint = roughLocation;
        do {
            currentPoint = bestPoint;
            List<ThreeDPoint> points = getNeighbor(currentPoint, 1, 5);
            bestPoint = selectTheBestPoint(points);
        } while (!currentPoint.equals(bestPoint));
        return currentPoint;
    }

    private int numberItemOutOfRange(ThreeDPoint point) {
        return Math.toIntExact(nanoBots.stream()
                .mapToInt(n -> n.getRadious() - n.manhattanDistance(point))
                .filter(d -> d < 0)
                .count());
    }


    private ThreeDPoint getRoughLocation() {
        ThreeDPoint currentPoint = null;
        ThreeDPoint bestPoint = new ThreeDPoint(0, 0, 0);
        for (int step = 10000000; step > 1; step = step / 10) {
            do {
                currentPoint = bestPoint;
                List<ThreeDPoint> points = getNeighbor(currentPoint, step, 2);
                bestPoint = selectTheBestPoint(points);
            } while (!currentPoint.equals(bestPoint));
        }
        return currentPoint;
    }

    private ThreeDPoint selectTheBestPoint(List<ThreeDPoint> points) {
        int minOutOfRange = nanoBots.size();
        ThreeDPoint bestPoint = nanoBots.get(0);
        for (ThreeDPoint point : points) {
            int outOfRange = numberItemOutOfRange(point);
            if (outOfRange < minOutOfRange) {
                minOutOfRange = outOfRange;
                bestPoint = point;
            } else if (outOfRange == minOutOfRange) {
                int bestPointDist = bestPoint.manhattanDistance(ORIGIN);
                int pointDist = point.manhattanDistance(ORIGIN);
                if (pointDist < bestPointDist) {
                    bestPoint = point;
                }
            }
        }
        return bestPoint;
    }

    private List<ThreeDPoint> getNeighbor(ThreeDPoint currentPoint, int step, int i) {
        List<ThreeDPoint> result = new ArrayList<>();
        for (int x = currentPoint.getX() - (step * i); x <= currentPoint.getX() + (step * i); x = x + step) {
            for (int y = currentPoint.getY() - (step * i); y <= currentPoint.getY() + (step * i); y = y + step) {
                for (int z = currentPoint.getZ() - (step * i); z <= currentPoint.getZ() + (step * i); z = z + step) {
                    result.add(new ThreeDPoint(x, y, z));
                }
            }
        }
        return result;
    }

    private int getIndexOfLargestValue(int[] directions) {
        int result = 0;
        int maxValue = 0;
        for (int i = 0; i < directions.length; i++) {
            int abs = Math.abs(directions[i]);
            if (abs > maxValue) {
                maxValue = abs;
                result = i;
            }
        }
        return result;
    }


}
