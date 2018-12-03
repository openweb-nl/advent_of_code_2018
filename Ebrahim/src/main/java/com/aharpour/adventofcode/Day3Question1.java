package com.aharpour.adventofcode;


import com.aharpour.adventofcode.utils.geometry.Rectangle;
import com.aharpour.adventofcode.utils.geometry.TwoDPoint;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class Day3Question1 {

    private List<Rectangle> rectangles;
    private int maxX;
    private int maxY;
    private int count = 0;
    private static final Pattern pattern = Pattern.compile("#\\d+ @ (\\d+),(\\d+): (\\d+)x(\\d+)");


    public int calculate(String string) {
        parseInput(string);

        for (int x = 0; x <= maxX; x++) {
            for (int y = 0; y <= maxX; y++) {
                TwoDPoint point = new TwoDPoint(x, y);
                boolean inOne = false;
                for (Rectangle rectangle : rectangles) {
                    if (rectangle.isInIt(point)) {
                        if (!inOne) {
                            inOne = true;
                        } else {
                            count++;
                            break;
                        }
                    }
                }

            }
        }
        return count;
    }

    private void parseInput(String string) {
        rectangles = Arrays.stream(string.split("\\s*\\n\\s*"))
                .map(pattern::matcher)
                .map(this::matcherToRect)
                .peek(this::updateMax)
                .collect(Collectors.toList());
    }

    private Rectangle matcherToRect(Matcher m) {
        if (m.matches()) {
            TwoDPoint lowerLeft = new TwoDPoint(parseInt(m.group(1)), parseInt(m.group(2)));
            return new Rectangle(lowerLeft, parseInt(m.group(3)), parseInt(m.group(4)));
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void updateMax(Rectangle rectangle) {
        TwoDPoint upperRight = rectangle.getUpperRight();
        if (maxX < upperRight.getX()) {
            maxX = upperRight.getX();
        }
        if (maxY < upperRight.getY()) {
            maxY = upperRight.getY();
        }
    }


}