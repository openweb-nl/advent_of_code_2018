package com.gklijs.adventofcode.day17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gklijs.adventofcode.utils.Pair;
import io.reactivex.Observable;
import io.reactivex.Single;

public class Day17 {

    private Day17() {
        //prevent instantiation
    }

    public static Single<String> waterTotal(Observable<String> input) {
        return input
            .reduce(new ArrayList<>(), Day17::parseClay)
            .map(c -> new Pair<>(c, boundaries(c)))
            .map(Day17::map)
            .map(Day17::flow)
            .map(Day17::waterReached);
    }

    public static Single<String> waterRetained(Observable<String> input) {
        return input
            .reduce(new ArrayList<>(), Day17::parseClay)
            .map(c -> new Pair<>(c, boundaries(c)))
            .map(Day17::map)
            .map(Day17::flow)
            .map(Day17::waterRetained);
    }

    private static int[] getIndex(int counter, boolean xFirst) {
        if (xFirst) {
            switch (counter) {
                case 0:
                    return new int[]{0, 1};
                case 1:
                    return new int[]{2};
                case 2:
                    return new int[]{3};
                default:
                    return new int[0];
            }
        } else {
            switch (counter) {
                case 0:
                    return new int[]{2, 3};
                case 1:
                    return new int[]{0};
                case 2:
                    return new int[]{1};
                default:
                    return new int[0];
            }
        }
    }

    private static List<int[]> parseClay(List<int[]> result, String input) {
        boolean xFirst = input.charAt(0) == 'x';
        int[] row = new int[4];
        StringBuilder builder = new StringBuilder();
        int counter = 0;
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                builder.append(c);
            } else if (builder.length() != 0) {
                for (int i : getIndex(counter, xFirst)) {
                    row[i] = Integer.parseInt(builder.toString());
                }
                builder.setLength(0);
                counter++;
            }
        }
        if (builder.length() != 0) {
            for (int i : getIndex(counter, xFirst)) {
                row[i] = Integer.parseInt(builder.toString());
            }
        }
        result.add(row);
        return result;
    }

    private static int[] boundaries(List<int[]> result) {
        int minX = result.stream().mapToInt(c -> c[0]).min().orElse(-1) - 1;
        int maxX = result.stream().mapToInt(c -> c[1]).max().orElse(-1) + 1;
        int minY = result.stream().mapToInt(c -> c[2]).min().orElse(-1);
        int maxY = result.stream().mapToInt(c -> c[3]).max().orElse(-1);
        return new int[]{minX, maxX, minY, maxY};
    }

    private static int[][] map(Pair<List<int[]>, int[]> input) {
        int[] b = input.getSecond();
        int[][] map = new int[b[3] - b[2] + 1][b[1] - b[0] + 1];
        for (int[] c : input.getFirst()) {
            for (int y = c[2]; y <= c[3]; y++) {
                for (int x = c[0]; x <= c[1]; x++) {
                    map[y - b[2]][x - b[0]] = 5;
                }
            }
        }
        map[0][500 - b[0]] = 1;
        return map;
    }

    private static String waterReached(int[][] map) {
        long counter = 0L;
        for (int[] row : map) {
            counter += Arrays.stream(row).filter(x -> x == 1 || x == 2).count();
        }
        return Long.toString(counter);
    }

    private static String waterRetained(int[][] map) {
        long counter = 0L;
        for (int[] row : map) {
            counter += Arrays.stream(row).filter(x -> x == 2).count();
        }
        return Long.toString(counter);
    }

    private static int[][] flow(int[][] map) {
        List<Pair<Integer, Integer>> water = new ArrayList<>();
        int[] row = map[0];
        for (int x = 0; x < row.length; x++) {
            if (row[x] == 1) {
                water.add(new Pair<>(x, 0));
            }
        }
        while (!water.isEmpty()) {
            Pair<Integer, Integer> next = water.get(0);
            if (next.getSecond() + 1 >= map.length) {
                water.remove(next);
                continue;
            }
            if (map[next.getSecond() + 1][next.getFirst()] == 0) {
                map[next.getSecond() + 1][next.getFirst()] = 1;
                next.changeSecond(y -> y + 1);
            } else if (map[next.getSecond() + 1][next.getFirst()] == 1) {
                water.remove(next);
            } else if (map[next.getSecond()][next.getFirst()] == 2) {
                water.remove(next);
            } else {
                int toLeft = next.getFirst();
                int toRight = next.getFirst();
                if (toLeft != 0) {
                    while (map[next.getSecond()][toLeft - 1] != 5) {
                        toLeft--;
                        map[next.getSecond()][toLeft] = 1;
                        if (map[next.getSecond() + 1][toLeft] <= 1) {
                            break;
                        }
                        if (toLeft == 1) {
                            toLeft--;
                            map[next.getSecond()][toLeft] = 1;
                            break;
                        }
                    }
                }
                if (toRight != row.length - 1) {
                    while (map[next.getSecond()][toRight + 1] != 5 && map[next.getSecond() + 1][toRight] > 1) {
                        toRight++;
                        map[next.getSecond()][toRight] = 1;
                        if (toRight == row.length - 2) {
                            toRight++;
                            map[next.getSecond()][toRight] = 1;
                            break;
                        }
                    }
                }
                if (map[next.getSecond() + 1][toRight] == 0) {
                    if (map[next.getSecond() + 1][toLeft] == 0) {
                        water.add(new Pair<>(toLeft, next.getSecond()));
                        int finalToRight = toRight;
                        next.changeFirst(x -> finalToRight);
                    } else {
                        int finalToRight = toRight;
                        next.changeFirst(x -> finalToRight);
                    }
                } else {
                    if (map[next.getSecond() + 1][toLeft] == 0) {
                        int finalToLeft = toLeft;
                        next.changeFirst(x -> finalToLeft);
                    } else {
                        for (int x = toLeft; x <= toRight; x++) {
                            map[next.getSecond()][x] = 2;
                        }
                        map[next.getSecond() - 1][next.getFirst()] = 1;
                        next.changeSecond(y -> y - 1);
                    }
                }
            }
        }
        return map;
    }
}
