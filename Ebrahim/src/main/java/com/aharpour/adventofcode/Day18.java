package com.aharpour.adventofcode;

public class Day18 {
    private char[][] map;

    public Day18(String input) {
        parseMap(input);
    }

    public boolean nextMinute() {
        boolean result = false;
        char[][] newMap = new char[map.length][map[0].length];
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                char c = map[y][x];
                newMap[y][x] = c;
                int[] state = getSurroundingStat(x, y);
                switch (c) {
                    case '.':
                        if (state[1] >= 3) {
                            newMap[y][x] = '|';
                            result = true;
                        }
                        break;
                    case '|':
                        if (state[2] >= 3) {
                            newMap[y][x] = '#';
                            result = true;
                        }
                        break;
                    case '#':
                        if (state[1] < 1 || state[2] < 1) {
                            newMap[y][x] = '.';
                            result = true;
                        }
                        break;
                    default:
                        throw new IllegalStateException();
                }
            }
        }
        map = newMap;
        return result;
    }

    private void parseMap(String input) {
        String[] rows = input.split("\\s*\\n\\s*");
        this.map = new char[rows.length][rows[0].length()];
        for (int y = 0; y < rows.length; y++) {
            map[y] = rows[y].toCharArray();
        }
    }

    public int[] getStat() {
        int[] result = new int[3];
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                result[charToIndex(map[y][x])] += 1;
            }
        }
        return result;
    }

    private int[] getSurroundingStat(int x, int y) {
        int[] result = new int[3];
        if (y > 0) {
            for (int i = Math.max(0, x - 1); i <= Math.min(map.length - 1, x + 1); i++) {
                result[charToIndex(map[y - 1][i])] += 1;
            }
        }
        if (y < map.length - 1) {
            for (int i = Math.max(0, x - 1); i <= Math.min(map.length - 1, x + 1); i++) {
                result[charToIndex(map[y + 1][i])] += 1;
            }
        }
        if (x > 0) {
            result[charToIndex(map[y][x - 1])] += 1;
        }
        if (x < map[0].length - 1) {
            result[charToIndex(map[y][x + 1])] += 1;
        }
        return result;
    }

    private int charToIndex(char c) {
        int result = 0;
        if (c == '|') {
            result = 1;
        } else if (c == '#') {
            result = 2;
        }
        return result;
    }


    public void print() {
        for (int y = 0; y < map.length; y++) {
            System.out.println(new String(map[y]));
        }
        System.out.println();
        System.out.println();
    }


}
