package com.aharpour.adventofcode;


import java.util.ArrayList;
import java.util.List;

public class Day5Question1 {
    protected List<Character> letters = new ArrayList<>();

    public int calculate(String string) {
        readCharsIntoList(string);
        return new ReactionCalculator(letters).calculate().size();
    }

    protected void readCharsIntoList(String string) {
        for (char c : string.toCharArray()) {
            letters.add(c);
        }
    }

    public static class ReactionCalculator {
        private List<Character> letters;

        protected ReactionCalculator(List<Character> letters) {
            this.letters = letters;
        }

        public List<Character> calculate() {

            int initialSize;
            int finalSize;
            do {
                initialSize = letters.size();
                letters = filter(letters);
                finalSize = letters.size();
            } while (initialSize > finalSize);
            return letters;
        }

        private List<Character> filter(List<Character> letters) {
            List<Character> result = new ArrayList<>();
            int i;
            for (i = 0; i < letters.size() - 1; i++) {
                Character first = letters.get(i);
                Character second = letters.get(i + 1);
                if (Character.toLowerCase(first) == Character.toLowerCase(second) &&
                        Math.abs(getNumericValue(first) - getNumericValue(second)) == 32) {
                    i += 1;
                    continue;
                }
                result.add(first);
            }
            if (letters.size() > i) {
                result.add(letters.get(i));
            }
            return result;
        }

        private int getNumericValue(Character c) {
            return (int) c;
        }
    }

}