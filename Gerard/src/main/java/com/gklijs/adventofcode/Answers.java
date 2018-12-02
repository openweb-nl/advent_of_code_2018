package com.gklijs.adventofcode;

import java.util.logging.Logger;

public class Answers {

    private static final Logger LOGGER = Logger.getLogger(Answers.class.getName());

    public static void main(String[] args) {
        if (args.length == 0) {
            printDay1();
        } else if (args.length == 1) {
            int day = Integer.parseInt(args[0]);
            switch (day) {
                case 1:
                    printDay1();
                    break;
                case 2:
                    printDay2();
                    break;
                default:
                    throw new InvalidUseException("Day " + day + " has no answers (yet)");
            }
        } else {
            throw new InvalidUseException("Use one argument to just run that day, or none to run all");
        }
    }

    private static void printDay1() {
        printDay1Awnser1();
        printDay1Awnser2();
    }

    private static void printDay1Awnser1() {
        Day1Question1.calculateFrequency(Utils.readLines("day1question1.txt").toObservable())
            .doOnSuccess(result -> LOGGER.info(() -> "day1question1: " + result))
            .subscribe();
    }

    private static void printDay1Awnser2() {
        Day1Question2.firstDoubleFrequency(Utils.readLines("day1question1.txt").toObservable())
            .doOnSuccess(result -> LOGGER.info(() -> "day1question2: " + result))
            .subscribe();
    }

    private static void printDay2() {
        throw new InvalidUseException("challange for day 2 is not available yet");
    }
}
