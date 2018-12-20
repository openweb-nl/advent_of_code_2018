package com.aharpour.adventofcode;

import com.aharpour.adventofcode.utils.IntPair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Ebrahim Aharpour
 * @since 12/20/2018
 */
public class Day20Question2 {

    private char[] input;
    private int index = 1;
    private final int minPath;
    private Map<IntPair, Integer> pointDistance = new HashMap<>();

    public Day20Question2(String input, int minPath) {
        this.input = input.toCharArray();
        this.minPath = minPath;
        if (this.input.length <= 1) {
            throw new IllegalArgumentException();
        }
    }

    public int calculate() {
        IntPair origin = new IntPair(0, 0);
        pointDistance.put(origin, 0);
        return calculate(0, origin);
    }
    public int calculate(int cv, IntPair coordinates) {
        Set<Integer> paths = new HashSet<>();
        int c = 0;
        IntPair currentCoordinates = coordinates;
        while (index < input.length) {
            char ch = input[index];
            index++;
            switch (ch) {
                case '(':
                    c = c + calculate(cv + c, currentCoordinates);
                    break;
                case ')':
                    paths.add(c);
                    return paths.stream().mapToInt(i -> i).min().orElse(0);
                case '|':
                    paths.add(c);
                    currentCoordinates = coordinates;
                    c = 0;
                    break;
                case '$':
                    paths.add(c);
                    //return pointDistance.values().stream().mapToInt(Integer::intValue).max().orElse(0);
                    return new Long(pointDistance.entrySet().stream().filter(i -> i.getValue() >= minPath).count()).intValue();
                case 'N':
                    currentCoordinates = new IntPair(currentCoordinates.getKey(), currentCoordinates.getValue() - 1);
                    c = handle(currentCoordinates, c, cv);
                    break;
                case 'E':
                    currentCoordinates = new IntPair(currentCoordinates.getKey() + 1, currentCoordinates.getValue());
                    c = handle(currentCoordinates, c, cv);
                    break;
                case 'S':
                    currentCoordinates = new IntPair(currentCoordinates.getKey(), currentCoordinates.getValue() + 1);
                    c = handle(currentCoordinates, c, cv);
                    break;
                case 'W':
                    currentCoordinates = new IntPair(currentCoordinates.getKey() - 1, currentCoordinates.getValue());
                    c = handle(currentCoordinates, c, cv);
                    break;
                default:
                    throw new IllegalArgumentException();

            }

        }
        throw new IllegalStateException();
    }

    private int handle(IntPair coordinates, int c, int cv) {

        c++;
        if (!pointDistance.containsKey(coordinates)) {
            pointDistance.put(coordinates, c + cv);
        }
        return c;
    }
}
