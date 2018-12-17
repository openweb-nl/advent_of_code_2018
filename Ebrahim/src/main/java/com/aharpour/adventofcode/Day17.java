package com.aharpour.adventofcode;

import com.aharpour.adventofcode.utils.IntPair;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day17 {
    private static final Pattern PATTERN = Pattern.compile("([xy])=(\\d+), ([xy])=(\\d+)..(\\d+)");
    private boolean[][] scan;
    private int xOffSet;
    private int yOffSet;
    protected Set<IntPair> wetSand = new HashSet<>();
    protected Set<IntPair> water = new HashSet<>();
    private int springX;
    private final boolean print;

    public Day17(int springX, String map, boolean print) {
        parseMap(map);
        this.springX = springX;
        this.print = print;
    }

    public void simulateWaterFlow() {
        calculate(springX - xOffSet, 0);
    }

    private boolean calculate(int x, int y) {
        if (y + 1 >= scan[0].length) {
            wetSand.add(new IntPair(x, y));
            print();
            return true;
        }
        if (!scan[x][y + 1] && calculate(x, y + 1)) {
            wetSand.add(new IntPair(x, y));
            print();
            return true;
        }
        List<IntPair> visited = new ArrayList<>();
        visited.add(new IntPair(x, y));
        boolean rightDrain = scanRight(x, y, visited);

        boolean leftDrain = scanLeft(x, y, visited);

        if (leftDrain || rightDrain) {
            wetSand.addAll(visited);
        } else {
            addToWater(visited);
        }
        print();
        return leftDrain || rightDrain;

    }

    private boolean scanLeft(int x, int y, List<IntPair> visited) {
        int i = x - 1;
        while (!scan[i][y]) {
            if ( handleItem(y, visited, i)) {
                return true;
            }
            i--;
        }
        return false;
    }

    private boolean scanRight(int x, int y, List<IntPair> visited) {
        int i = x + 1;
        while (!scan[i][y]) {
            if (handleItem(y, visited, i)) {
                return true;
            }
            i++;
        }
        return false;
    }

    private boolean handleItem(int y, List<IntPair> visited, int i) {
        visited.add(new IntPair(i, y));
        return !scan[i][y + 1] && calculate(i, y + 1);
    }

    private void addToWater(List<IntPair> visited) {
        water.addAll(visited);
        for (IntPair intPair : visited) {
            scan[intPair.getKey()][intPair.getValue()] = true;
        }
    }

    private void print() {
        if (print) {
            forcePrint();
        }
    }

    public void forcePrint() {
        for (int y = 0; y < scan[0].length; y++) {
            for (int x = 0; x < scan.length; x++) {

                char c = scan[x][y] ? '#' : '.';
                IntPair point = new IntPair(x, y);
                if (water.contains(point)) {
                    c = '~';
                }
                if (wetSand.contains(point)) {
                    c = '|';
                }
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    private void parseMap(String map) {
        Matcher matcher = PATTERN.matcher(map);
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        List<Row> rows = new ArrayList<>();
        while (matcher.find()) {
            Row row = matcherToRow(matcher);
            if (maxX < row.xTo) {
                maxX = row.xTo;
            }
            if (minX > row.xFrom) {
                minX = row.xFrom;
            }
            if (maxY < row.yTo) {
                maxY = row.yTo;
            }
            if (minY > row.yFrom) {
                minY = row.yFrom;
            }
            rows.add(row);
        }
        xOffSet = (minX - 1);
        yOffSet = minY;

        scan = new boolean[maxX + 2 - xOffSet][maxY - yOffSet + 1];
        updateScan(rows);

    }

    private void updateScan(List<Row> rows) {
        for (Row row : rows) {
            for (int x = row.xFrom; x <= row.xTo; x++) {
                for (int y = row.yFrom; y <= row.yTo; y++) {
                    scan[x - xOffSet][y - yOffSet] = true;
                }
            }
        }
    }

    private Row matcherToRow(Matcher matcher) {
        Row result = new Row();
        String letter = matcher.group(1);
        int value = Integer.parseInt(matcher.group(2));
        if ("x".equals(letter)) {
            result.xTo = result.xFrom = value;
        } else {
            result.yTo = result.yFrom = value;
        }
        letter = matcher.group(3);
        int value1 = Integer.parseInt(matcher.group(4));
        int value2 = Integer.parseInt(matcher.group(5));
        if ("x".equals(letter)) {
            result.xFrom = value1;
            result.xTo = value2;
        } else {
            result.yFrom = value1;
            result.yTo = value2;
        }
        return result;
    }

    @Data
    private class Row {
        private int xFrom;
        private int xTo;
        private int yFrom;
        private int yTo;
    }
}
