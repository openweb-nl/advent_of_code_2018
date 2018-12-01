package com.aharpour.adventofcode.utils;

import java.util.Arrays;

public class StringUtils {

    public static int[] intStringToIntArray(String string) {
        return string.trim().chars()
                .filter(i -> i >= 48 && i <= 57)
                .map(i -> i - 48).toArray();
    }

    public static int[] stringToIntArray(String string, String delimiter) {
        return Arrays.stream(string.trim().split(delimiter))
                .filter(StringUtils::isNotBlank)
                .mapToInt(Integer::parseInt).toArray();
    }

    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
