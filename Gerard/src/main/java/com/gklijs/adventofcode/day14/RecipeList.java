package com.gklijs.adventofcode.day14;

class RecipeList {

    private final int[] grades;
    private int elfOne = 0;
    private int elfTwo = 1;
    private int gradedTill = 1;

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

    int complete(String till) {
        while (!till.equals(getFirstXOfLastTen(till.length()))) {
            step();
        }
        return gradedTill - 9;
    }

    private void step() {
        int recipeOne = grades[elfOne];
        int recipeTwo = grades[elfTwo];
        int nextGrade = recipeOne + recipeTwo;
        if (nextGrade > 9) {
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

    private String getFirstXOfLastTen(int x) {
        if (gradedTill < 10) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = gradedTill - 9; i < gradedTill - (9 - x); i++) {
            builder.append(grades[i]);
        }
        return builder.toString();
    }
}
