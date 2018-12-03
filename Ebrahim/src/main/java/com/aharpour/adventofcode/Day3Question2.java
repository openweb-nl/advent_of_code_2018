package com.aharpour.adventofcode;


import com.aharpour.adventofcode.utils.ArrayBiReducer;
import com.aharpour.adventofcode.utils.geometry.Rectangle;
import com.aharpour.adventofcode.utils.geometry.TwoDPoint;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class Day3Question2 {

    private List<Rectangle> rectangles;
    private int maxX;
    private int maxY;
    private static final Pattern pattern = Pattern.compile("#\\d+ @ (\\d+),(\\d+): (\\d+)x(\\d+)");

    public int calculate(String string) {
        parseInput(string);
        return ArrayBiReducer.<Rectangle, Collection<Rectangle>, Set<Rectangle>, Optional<Integer>>builder()
                .supplier(HashSet::new)
                .operator(Day3Question2::operator)
                .accumulator(Set::addAll)
                .finisher(this::finisher)
                .build()
                .operate(rectangles.toArray(new Rectangle[0]))
                .orElseThrow(IllegalArgumentException::new);
    }

    private static Collection<Rectangle> operator(Rectangle r1, Rectangle r2) {
        return r1.intersection(r2)
                .map(i -> new HashSet<>(Arrays.asList(r1, r2)))
                .orElse(new HashSet<>());
    }

    private Optional<Integer> finisher(Set<Rectangle> rectanglesWithOverlap) {
        for (int i = 0; i < rectangles.size(); i++) {
            Rectangle rectangle = rectangles.get(i);
            if (!rectanglesWithOverlap.contains(rectangle)) {
                return Optional.of(i + 1);
            }
        }
        return Optional.empty();
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