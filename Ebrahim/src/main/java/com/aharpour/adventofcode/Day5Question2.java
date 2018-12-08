package com.aharpour.adventofcode;


import java.util.List;
import java.util.stream.Collectors;

public class Day5Question2 extends Day5Question1 {

    public int calculate(String string) {
        readCharsIntoList(string);
        int min = letters.size();
        for (int i = 65; i < 91; i++) {
            char c = Character.toChars(i)[0];
            List<Character> copy = letters.stream()
                    .filter(k -> Character.toUpperCase(k) != c)
                    .collect(Collectors.toList());
            int size = new ReactionCalculator(copy).calculate().size();
            if (size < min) {
                min = size;
            }
        }
        return min;
    }


}