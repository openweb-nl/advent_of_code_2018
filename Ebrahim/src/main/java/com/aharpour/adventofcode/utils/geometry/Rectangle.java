package com.aharpour.adventofcode.utils.geometry;

import java.util.Optional;

import lombok.Data;

@Data
public class Rectangle {
    private final TwoDPoint lowerLeft;
    private final TwoDPoint upperRight;

    public Rectangle(TwoDPoint lowerLeft, TwoDPoint upperRight) {
        if (upperRight.leftOf(lowerLeft) || upperRight.belowOf(lowerLeft)) {
            throw new IllegalArgumentException();
        }
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
    }

    public Rectangle(TwoDPoint lowerLeft, int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException();
        }
        this.lowerLeft = lowerLeft;
        this.upperRight = new TwoDPoint(lowerLeft.getX() + (width - 1), lowerLeft.getY() + (height - 1));
    }

    public boolean isInIt(TwoDPoint point) {
        boolean result = false;
        if (lowerLeft.getX() <= point.getX() && lowerLeft.getY() <= point.getY()
                && point.getX() <= upperRight.getX() && point.getY() <= upperRight.getY()
                ) {
            result = true;
        }
        return result;
    }

    public int area() {
        return (upperRight.getX() - lowerLeft.getX()) * (upperRight.getY() - lowerLeft.getY());
    }

    public Optional<Rectangle> intersection(Rectangle another) {
        Optional<Rectangle> result = Optional.empty();
        TwoDPoint newLowerLeft = new TwoDPoint(Math.max(another.getLowerLeft().getX(), lowerLeft.getX()),
                Math.max(another.getLowerLeft().getY(), lowerLeft.getY()));
        TwoDPoint newUpperRight = new TwoDPoint(Math.min(another.getUpperRight().getX(),
                upperRight.getX()), Math.min(another.getUpperRight().getY(), upperRight.getY()));
        if (newLowerLeft.leftOf(newUpperRight) && newLowerLeft.belowOrEqualOf(newUpperRight)) {
            result = Optional.of(new Rectangle(newLowerLeft, newUpperRight));
        }
        return result;

    }
}