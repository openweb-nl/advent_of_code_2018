package com.gklijs.adventofcode.day11;

import java.util.Arrays;

import io.reactivex.Observable;
import io.reactivex.Single;

public class Day11 {

    private Day11() {
        //prevent instantiation
    }

    public static Single<String> sizeThree(Observable<String> input) {
        return input
            .lastOrError()
            .map(Integer::parseInt)
            .map(Day11::powerGrid)
            .map(x -> Day11.getMax(x, 3))
            .map(r -> r[1] + "," + r[2]);
    }

    public static Single<String> sizeVariable(Observable<String> input) {
        return input
            .lastOrError()
            .map(Integer::parseInt)
            .map(Day11::powerGrid)
            .map(Day11::var)
            .map(r -> r[0] + "," + r[1] + "," + r[2]);
    }

    private static int[][] powerGrid(int input) {
        int[][] grid = new int[300][300];
        for (int x = 0; x < 300; x++) {
            for (int y = 0; y < 300; y++) {
                int rackId = x + 11;
                int mid = ((rackId * (y + 1)) + input) * rackId;
                grid[x][y] = ((mid % 1000) / 100) - 5;
            }
        }
        return grid;
    }

    private static int[] getMax(int[][] input, int size) {
        int[] result = new int[]{-46, 0, 0};
        for (int x = 0; x < (300 - size); x++) {
            int[] temp = new int[size];
            for (int z = 0; z < size; z++) {
                for (int c = 0; c < size; c++) {
                    temp[z] += input[x + c][z];
                }
            }
            if (Arrays.stream(temp).sum() > result[0]) {
                result[0] = Arrays.stream(temp).sum();
                result[1] = x + 1;
                result[2] = 1;
            }
            for (int y = size; y < 299; y++) {
                int replace = 0;
                for (int c = 0; c < size; c++) {
                    replace += input[x + c][y];
                }
                temp[y % size] = replace;
                if (Arrays.stream(temp).sum() > result[0]) {
                    result[0] = Arrays.stream(temp).sum();
                    result[1] = x + 1;
                    result[2] = y - size + 2;
                }
            }
        }
        return result;
    }

    private static int[] var(int[][] input) {
        int[] max = getMax(input, 3);
        int size = 3;
        for (int z = 4; z < 20; z++) {
            int[] now = getMax(input, z);
            if (now[0] > max[0]) {
                max = now;
                size = z;
            }
        }
        return new int[]{max[1], max[2], size};
    }
}
