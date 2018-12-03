package com.aharpour.adventofcode.utils.geometry;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TwoDPoint {
    private int x;
    private int y;

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
}
