package com.gklijs.adventofcode;

import java.util.logging.Logger;

import io.reactivex.Observable;
import io.reactivex.Single;

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

    @FunctionalInterface
    interface SingleIntAnswer {
        Single<Integer> getAnswer(Observable<String> lines);
    }

    private static void printIntAnswer(String task, String fileName, SingleIntAnswer singleIntAnswer){
        Timer timer = new Timer(task);
        singleIntAnswer.getAnswer(Utils.readLines(fileName).toObservable())
            .doOnSubscribe(something -> timer.start())
            .doAfterTerminate(timer::stop)
            .doOnSuccess(result -> LOGGER.info(() -> task + ": " + result))
            .subscribe();
    }

    private static void printDay1() {
        printIntAnswer("day1question1", "day1question1.txt", Day1Question1::calculateFrequency);
        printIntAnswer("day1question2", "day1question1.txt", Day1Question2::firstDoubleFrequency);
    }

    private static void printDay2() {
        throw new InvalidUseException("challenge for day 2 is not available yet");
    }
}
