package com.gklijs.adventofcode.day14;

class RecipeList {

    private final int[] grades;
    private int elfOne = 0;
    private int elfTwo = 1;
    private int gradedTill = 1;
    private boolean lastBiggerThenTen;

    RecipeList(int maxSize) {
        grades = new int[maxSize];
        grades[0] = 3;
        grades[1] = 7;
    }

    int[] complete() {
        while (gradedTill < (grades.length - 1)) {
            step();
        }
        return grades;
    }

    int complete(int[] till) {
        while (!firstXOfLastTenAreSame(till)) {
            step();
        }
        return lastBiggerThenTen ? gradedTill - 10 : gradedTill - 9;
    }

    private void step() {
        int recipeOne = grades[elfOne];
        int recipeTwo = grades[elfTwo];
        int nextGrade = recipeOne + recipeTwo;
        lastBiggerThenTen = nextGrade > 9;
        if (lastBiggerThenTen) {
            gradedTill++;
            grades[gradedTill] = 1;
            if (gradedTill < (grades.length - 1)) {
                gradedTill++;
                grades[gradedTill] = nextGrade - 10;
            }
        } else {
            gradedTill++;
            grades[gradedTill] = nextGrade;
        }
        elfOne = (elfOne + 1 + recipeOne) % (gradedTill + 1);
        elfTwo = (elfTwo + 1 + recipeTwo) % (gradedTill + 1);
    }

    private boolean firstXOfLastTenAreSame(int[] till) {
        if (gradedTill < 10) {
            return false;
        }
        for (int i = 0; i < till.length; i++) {
            if (till[i] != grades[gradedTill - 9 + i - (lastBiggerThenTen ? 1 : 0)]) {
                return false;
            }
        }
        return true;
    }
}
