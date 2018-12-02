package com.aharpour.adventofcode;


public class Day2Question2 {

    public static String calculate(String string) {
        String[] split = string.split("\\s+");
        for (int i = 0; i < split.length - 1; i++) {
            for (int j = i + 1; j < split.length; j++) {
                String first = split[i];
                String second = split[j];
                String intersection = intersection(first.toCharArray(), second.toCharArray());
                if (first.length() == second.length() && second.length() == intersection.length() + 1) {
                    return intersection;
                }
            }
        }
        return null;
    }

    private static String intersection(char[] first, char[] second) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < Math.min(first.length, second.length); i++) {
            if (first[i] == second[i]) {
                builder.append(first[i]);
            }
        }
        return builder.toString();
    }
}