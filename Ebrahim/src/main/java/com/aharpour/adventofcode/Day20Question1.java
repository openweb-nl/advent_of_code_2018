package com.aharpour.adventofcode;

import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.Set;


import static java.lang.Math.max;

/**
 * @author Ebrahim Aharpour
 * @since 12/20/2018
 */
public class Day20Question1 {

    private int maxTerminalPath = 0;
    private char[] input;
    private int index = 1;

    public Day20Question1(String input) {
        this.input = input.toCharArray();
        if (this.input.length <= 1) {
            throw new IllegalArgumentException();
        }
    }

    public int calculate() {
        return calculate(0);
    }

    private int calculate(int cv) {
        Set<Integer> paths = new HashSet<>();
        int c = 0;
        while (index < input.length) {
            char ch = input[index];
            index++;
            switch (ch) {
                case '(':
                    c = c + calculate(cv + c);
                    break;
                case ')':
                    paths.add(c);
                    IntSummaryStatistics stat =
                            paths.stream().mapToInt(i -> i)
                                    .summaryStatistics();
                    int min = stat.getMin();
                    if (min == 0) {
                        maxTerminalPath = max(maxTerminalPath, cv + (stat.getMax() / 2));
                    } else {
                        maxTerminalPath = max(maxTerminalPath, cv + stat.getMax());
                    }
                    return min;
                case '|':
                    paths.add(c);
                    c = 0;
                    break;
                case '$':
                    paths.add(c);
                    return max(cv + paths.stream().mapToInt(i -> i).max().getAsInt(), maxTerminalPath);
                case 'N':
                case 'E':
                case 'S':
                case 'W':
                    c++;
                    break;
                default:
                    throw new IllegalArgumentException();

            }
        }
        return 0;
    }
}
