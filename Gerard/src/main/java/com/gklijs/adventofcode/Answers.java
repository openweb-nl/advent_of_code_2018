package com.gklijs.adventofcode;

import java.util.logging.Logger;

import com.gklijs.adventofcode.day1.Day1;
import com.gklijs.adventofcode.day2.Day2;
import com.gklijs.adventofcode.day3.Day3;
import io.reactivex.Observable;
import io.reactivex.Single;

public class Answers {

    private static final Logger LOGGER = Logger.getLogger(Answers.class.getName());

    public static void main(String[] args) {
        if (args.length == 0) {
            printAll();
        } else if (args.length == 1) {
            int day = Integer.parseInt(args[0]);
            switch (day) {
                case 1:
                    printDay1();
                    break;
                case 2:
                    printDay2();
                    break;
                case 3:
                    printDay3();
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

    @FunctionalInterface
    interface SingleStringAnswer {
        Single<String> getAnswer(Observable<String> lines);
    }

    private static void printIntAnswer(String task, String fileName, SingleIntAnswer singleIntAnswer){
        setTimeAndStart(task, singleIntAnswer.getAnswer(Utils.readLines(fileName).toObservable())
            .doOnSuccess(result -> LOGGER.info(() -> task + ": " + result)));
    }

    private static void printStringAnswer(String task, String fileName, SingleStringAnswer singleStringAnswer){
        setTimeAndStart(task, singleStringAnswer.getAnswer(Utils.readLines(fileName).toObservable())
        .doOnSuccess(result -> LOGGER.info(() -> task + ": " + result)));
    }

    private static void setTimeAndStart(String task, Single job){
        Timer timer = new Timer(task);
        job
            .doOnSubscribe(something -> timer.start())
            .doAfterTerminate(timer::stop)
            .subscribe();
    }

    private static void printAll(){
        printDay1();
        printDay2();
    }

    private static void printDay1() {
        printIntAnswer("day1question1", "day1.txt", Day1::calculateFrequency);
        printIntAnswer("day1question2", "day1.txt", Day1::firstDoubleFrequency);
    }

    private static void printDay2() {
        printIntAnswer("day2question1", "day2.txt", Day2::checksum);
        printStringAnswer("day2question2", "day2.txt", Day2::commonLetters);
    }

    private static void printDay3() {
        printIntAnswer("day3question1", "day3.txt", Day3::multipleClaims);
        printIntAnswer("day3question2", "day3.txt", Day3::noClaims);
    }
}
