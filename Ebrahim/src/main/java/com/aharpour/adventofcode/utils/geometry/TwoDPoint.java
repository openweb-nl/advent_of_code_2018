package com.aharpour.adventofcode.utils.geometry;

import java.util.Comparator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


import static java.lang.Math.abs;

@Data
@EqualsAndHashCode(exclude = "velocity")
@AllArgsConstructor
public class TwoDPoint {
    private int x;
    private int y;
    private TwoDPoint velocity;

    public TwoDPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void secondsForward(int seconds) {
        if (velocity == null) {
            throw new IllegalArgumentException();
        }
        x += seconds * velocity.x;
        y += seconds * velocity.y;
    }

    public boolean leftOf(TwoDPoint another) {
        return x < another.x;
    }

    public boolean leftOrEqualOf(TwoDPoint another) {
        return x <= another.x;
    }

    public boolean belowOf(TwoDPoint another) {
        return y < another.y;
    }

    public boolean belowOrEqualOf(TwoDPoint another) {
        return y <= another.y;
    }

    public int manhattanDistance(TwoDPoint another) {
        return manhattanDistance(another.x, another.y);
    }

    public int manhattanDistance(int x, int y) {
        return abs(this.x - x) + abs(this.y - y);
    }

    public static Comparator<TwoDPoint> getComparator() {
        return Comparator.comparingInt(TwoDPoint::getY)
                .thenComparing(Comparator.comparingInt(TwoDPoint::getX));
    }
}
