package com.aharpour.adventofcode.utils.geometry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;

import java.util.Comparator;

import static java.lang.Math.abs;

@Data
@EqualsAndHashCode(of = {"x", "y", "z", "u"})
@AllArgsConstructor
public class FourDPoint implements Cloneable {
    private int x;
    private int y;
    private int z;
    private int u;

    public boolean leftOf(FourDPoint another) {
        return x < another.x;
    }

    public boolean leftOrEqualOf(FourDPoint another) {
        return x <= another.x;
    }

    public boolean belowOf(FourDPoint another) {
        return y < another.y;
    }

    public boolean belowOrEqualOf(FourDPoint another) {
        return y <= another.y;
    }

    public boolean lowerOf(FourDPoint another) {
        return z < another.z;
    }

    public boolean lowerOrEqualOf(FourDPoint another) {
        return z <= another.z;
    }

    public boolean belowInFourthDOf(FourDPoint another) {
        return u < another.u;
    }

    public boolean belowOrEqualInFourthDOf(FourDPoint another) {
        return z <= another.z;
    }



    public int manhattanDistance(FourDPoint another) {
        return manhattanDistance(another.x, another.y, another.z, another.u);
    }

    public int manhattanDistance(int x, int y, int z, int u) {
        return abs(this.x - x) + abs(this.y - y) + abs(this.z - z) + abs(this.u - u);
    }

    public static Comparator<FourDPoint> getComparator() {
        return Comparator.comparingInt(FourDPoint::getU)
                .thenComparingInt(FourDPoint::getZ)
                .thenComparingInt(FourDPoint::getY)
                .thenComparingInt(FourDPoint::getX);
    }

    @Override
    @SneakyThrows
    public Object clone() {
        return super.clone();
    }
}
