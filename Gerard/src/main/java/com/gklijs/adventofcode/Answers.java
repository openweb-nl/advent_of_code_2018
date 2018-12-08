package com.gklijs.adventofcode;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.logging.Logger;

import com.gklijs.adventofcode.day1.Day1;
import com.gklijs.adventofcode.day2.Day2;
import com.gklijs.adventofcode.day3.Day3;
import com.gklijs.adventofcode.day4.Day4;
import com.gklijs.adventofcode.day5.Day5;
import com.gklijs.adventofcode.day6.Day6;
import com.gklijs.adventofcode.day7.Day7;
import com.gklijs.adventofcode.day8.Day8;
import com.gklijs.adventofcode.utils.Pair;
import io.reactivex.Observable;
import io.reactivex.Single;

public class Answers {

    private static final Logger LOGGER = Logger.getLogger(Answers.class.getName());
    private static final Map<Integer, Pair<BiFunction<String, String, String>, BiFunction<String, String, String>>> ANS = new HashMap<>();
    private static final String FILE_FORMAT = "day%d.txt";
    private static final String FIRST_TASK_FORMAT = "first task for day %d";
    private static final String SECOND_TASK_FORMAT = "second task for day %d";

    static {
        ANS.put(1, new Pair<>(
            (t, f) -> printIntAnswer(t, f, Day1::calculateFrequency),
            (t, f) -> printIntAnswer(t, f, Day1::firstDoubleFrequency)
        ));
        ANS.put(2, new Pair<>(
            (t, f) -> printIntAnswer(t, f, Day2::checksum),
            (t, f) -> printStringAnswer(t, f, Day2::commonLetters)
        ));
        ANS.put(3, new Pair<>(
            (t, f) -> printIntAnswer(t, f, Day3::multipleClaims),
            (t, f) -> printIntAnswer(t, f, Day3::noClaims)
        ));
        ANS.put(4, new Pair<>(
            (t, f) -> printPairAnswer(t, f, Day4::bestOpportunity),
            (t, f) -> printPairAnswer(t, f, Day4::mostAtSameMinute)
        ));
        ANS.put(5, new Pair<>(
            (t, f) -> printIntAnswer(t, f, Day5::react),
            (t, f) -> printIntAnswer(t, f, Day5::reactDeleteReact)
        ));
        ANS.put(6, new Pair<>(
            (t, f) -> printIntAnswer(t, f, Day6::largestFiniteArea),
            (t, f) -> printIntAnswer(t, f, x -> Day6.toAllLessThen(x, 10000))
        ));
        ANS.put(7, new Pair<>(
            (t, f) -> printStringAnswer(t, f, Day7::getOrder),
            (t, f) -> printIntAnswer(t, f, x -> Day7.work(x, 5, 60))
        ));
        ANS.put(8, new Pair<>(
            (t, f) -> printIntAnswer(t, f, Day8::allMetaData),
            (t, f) -> printIntAnswer(t, f, Day8::getValue)
        ));
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            printAll();
        } else if (args.length == 1) {
            int day = Integer.parseInt(args[0]);
            if (ANS.containsKey(day)) {
                Pair<BiFunction<String, String, String>, BiFunction<String, String, String>> pair = ANS.get(day);
                pair.getFirst().apply(String.format(FIRST_TASK_FORMAT, day), String.format(FILE_FORMAT, day));
                pair.getSecond().apply(String.format(SECOND_TASK_FORMAT, day), String.format(FILE_FORMAT, day));
            } else {
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

    @FunctionalInterface
    interface SinglePairAnswer {

        Single<Pair<Integer, Integer>> getAnswer(Observable<String> lines);
    }

    private static String printIntAnswer(String task, String fileName, SingleIntAnswer singleIntAnswer) {
        return setTimeAndStart(task, singleIntAnswer.getAnswer(Utils.readLines(fileName).toObservable())
            .doOnSuccess(result -> LOGGER.info(() -> task + ": " + result)));
    }

    private static String printStringAnswer(String task, String fileName, SingleStringAnswer singleStringAnswer) {
        return setTimeAndStart(task, singleStringAnswer.getAnswer(Utils.readLines(fileName).toObservable())
            .doOnSuccess(result -> LOGGER.info(() -> task + ": " + result)));
    }

    private static String printPairAnswer(String task, String fileName, SinglePairAnswer singlePairAnswer) {
        return setTimeAndStart(task, singlePairAnswer.getAnswer(Utils.readLines(fileName).toObservable())
            .doOnSuccess(result -> LOGGER.info(() -> task + ": " + result.getFirst() * result.getSecond())));
    }

    private static String setTimeAndStart(String task, Single job) {
        Timer timer = new Timer(task);
        job
            .doOnSubscribe(something -> timer.start())
            .doAfterTerminate(timer::stop)
            .subscribe();
        return task;
    }

    private static int startBoth(Map.Entry<Integer, Pair<BiFunction<String, String, String>, BiFunction<String, String, String>>> entry) {
        entry.getValue().getFirst().apply(String.format(FIRST_TASK_FORMAT, entry.getKey()), String.format(FILE_FORMAT, entry.getKey()));
        entry.getValue().getSecond().apply(String.format(SECOND_TASK_FORMAT, entry.getKey()), String.format(FILE_FORMAT, entry.getKey()));
        return 2;
    }

    private static void printAll() {
        int tasks = ANS.entrySet().stream().mapToInt(Answers::startBoth).sum();
        LOGGER.info(() -> "Started " + tasks + " tasks");
    }
}
