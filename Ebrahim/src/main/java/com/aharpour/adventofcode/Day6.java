package com.aharpour.adventofcode;

import java.util.Arrays;

import com.aharpour.adventofcode.utils.geometry.TwoDPoint;


import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * @author Ebrahim Aharpour
 * @since 12/7/2018
 */
public class Day6 {

    protected TwoDPoint[] points;
    protected int minX = Integer.MAX_VALUE;
    protected int maxX = Integer.MIN_VALUE;
    protected int minY = Integer.MAX_VALUE;
    protected int maxY = Integer.MIN_VALUE;

    protected void readInput(String string) {
        points = Arrays.stream(string.split("\\s*\\n\\s*"))
                .map(s -> s.split("\\s*,\\s+"))
                .map(s -> new TwoDPoint(Integer.parseInt(s[0]), Integer.parseInt(s[1])))
                .peek(this::setMinAndMax)
                .toArray(TwoDPoint[]::new);
    }

    private void setMinAndMax(TwoDPoint p) {
        minX = min(minX, p.getX());
        minY = min(minY, p.getY());
        maxX = max(maxX, p.getX());
        maxY = max(maxY, p.getY());
    }
}
