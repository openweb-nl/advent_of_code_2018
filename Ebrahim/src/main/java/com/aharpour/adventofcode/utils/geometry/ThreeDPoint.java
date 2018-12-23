package com.aharpour.adventofcode.utils.geometry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;

import java.util.Comparator;

import static java.lang.Math.abs;

@Data
@EqualsAndHashCode(of = {"x", "y", "z"})
@AllArgsConstructor
public class ThreeDPoint implements Cloneable {
    private int x;
    private int y;
    private int z;

    public boolean leftOf(ThreeDPoint another) {
        return x < another.x;
    }

    public boolean leftOrEqualOf(ThreeDPoint another) {
        return x <= another.x;
    }

    public boolean belowOf(ThreeDPoint another) {
        return y < another.y;
    }

    public boolean belowOrEqualOf(ThreeDPoint another) {
        return y <= another.y;
    }

    public boolean lowerOf(ThreeDPoint another) {
        return z < another.z;
    }

    public boolean lowerOrEqualOf(ThreeDPoint another) {
        return z <= another.z;
    }

    public int manhattanDistance(ThreeDPoint another) {
        return manhattanDistance(another.x, another.y, another.z);
    }

    public int manhattanDistance(int x, int y, int z) {
        return abs(this.x - x) + abs(this.y - y) + abs(this.z - z);
    }

    public static Comparator<ThreeDPoint> getComparator() {
        return Comparator.comparingInt(ThreeDPoint::getZ)
                .thenComparingInt(ThreeDPoint::getY)
                .thenComparingInt(ThreeDPoint::getX);
    }

    @Override
    @SneakyThrows
    public Object clone()  {
        return super.clone();
    }
}
