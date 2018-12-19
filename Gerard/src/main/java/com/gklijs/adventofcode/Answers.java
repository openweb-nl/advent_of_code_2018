package com.gklijs.adventofcode;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;

import com.gklijs.adventofcode.day1.Day1;
import com.gklijs.adventofcode.day10.Day10;
import com.gklijs.adventofcode.day11.Day11;
import com.gklijs.adventofcode.day12.Day12;
import com.gklijs.adventofcode.day13.Day13;
import com.gklijs.adventofcode.day14.Day14;
import com.gklijs.adventofcode.day15.Day15;
import com.gklijs.adventofcode.day16.Day16;
import com.gklijs.adventofcode.day17.Day17;
import com.gklijs.adventofcode.day18.Day18;
import com.gklijs.adventofcode.day19.Day19;
import com.gklijs.adventofcode.day2.Day2;
import com.gklijs.adventofcode.day3.Day3;
import com.gklijs.adventofcode.day4.Day4;
import com.gklijs.adventofcode.day5.Day5;
import com.gklijs.adventofcode.day6.Day6;
import com.gklijs.adventofcode.day7.Day7;
import com.gklijs.adventofcode.day8.Day8;
import com.gklijs.adventofcode.day9.Day9;
import com.gklijs.adventofcode.errors.InvalidUseException;
import com.gklijs.adventofcode.utils.Pair;
import io.reactivex.Observable;
import io.reactivex.Single;

public class Answers {

    private static final Logger LOGGER = Logger.getLogger(Answers.class.getName());
    static final Map<Integer, Pair<Function<Observable<String>, Single<String>>, Function<Observable<String>, Single<String>>>> ANS = new HashMap<>();
    private static final String FILE_FORMAT = "day%d.txt";
    private static final String FIRST_TASK_FORMAT = "first task for day %d";
    private static final String SECOND_TASK_FORMAT = "second task for day %d";

    static {
        ANS.put(1, new Pair<>(Day1::calculateFrequency, Day1::firstDoubleFrequency));
        ANS.put(2, new Pair<>(Day2::checksum, Day2::commonLetters));
        ANS.put(3, new Pair<>(Day3::multipleClaims, Day3::noClaims));
        ANS.put(4, new Pair<>(Day4::bestOpportunity, Day4::mostAtSameMinute));
        ANS.put(5, new Pair<>(Day5::react, Day5::reactDeleteReact));
        ANS.put(6, new Pair<>(Day6::largestFiniteArea, x -> Day6.toAllLessThen(x, 100000)));
        ANS.put(7, new Pair<>(Day7::getOrder, x -> Day7.work(x, 5, 60)));
        ANS.put(8, new Pair<>(Day8::allMetaData, Day8::getValue));
        ANS.put(9, new Pair<>(Day9::winningScore, x -> Day9.winningScore(x, 100)));
        ANS.put(10, new Pair<>(Day10::displayStars, Day10::stepsNeeded));
        ANS.put(11, new Pair<>(Day11::sizeThree, Day11::sizeVariable));
        ANS.put(12, new Pair<>(Day12::plantIndex, Day12::plantIndexTwo));
        ANS.put(13, new Pair<>(Day13::firstCrash, Day13::lastCard));
        ANS.put(14, new Pair<>(Day14::tenAfter, Day14::doTill));
        ANS.put(15, new Pair<>(Day15::lastTurn, Day15::noDeadElf));
        ANS.put(16, new Pair<>(Day16::threeOrMoreMatches, Day16::getResult));
        ANS.put(17, new Pair<>(Day17::waterTotal, Day17::waterRetained));
        ANS.put(18, new Pair<>(Day18::afterTen, Day18::afterBillion));
        ANS.put(19, new Pair<>(Day19::first, Day19::second));
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            printAll();
        } else if (args.length == 1) {
            int day = Integer.parseInt(args[0]);
            if (ANS.containsKey(day)) {
                Pair<Function<Observable<String>, Single<String>>, Function<Observable<String>, Single<String>>> pair = ANS.get(day);
                printAnswer(String.format(FIRST_TASK_FORMAT, day), String.format(FILE_FORMAT, day), pair.getFirst());
                printAnswer(String.format(SECOND_TASK_FORMAT, day), String.format(FILE_FORMAT, day), pair.getSecond());
            } else {
                throw new InvalidUseException("Day " + day + " has no answers (yet)");
            }
        } else {
            throw new InvalidUseException("Use one argument to just run that day, or none to run all");
        }
    }

    private static void printAnswer(String task, String fileName, Function<Observable<String>, Single<String>> function) {
        setTimeAndStart(task, function.apply(Utils.readLines(fileName).toObservable())
            .doOnSuccess(result -> LOGGER.info(() -> task + ": " + result)));
    }

    private static void setTimeAndStart(String task, Single job) {
        Timer timer = new Timer(task);
        job
            .doOnSubscribe(something -> timer.start())
            .doAfterTerminate(timer::stop)
            .subscribe();
    }

    private static int startBoth(Map.Entry<Integer, Pair<Function<Observable<String>, Single<String>>, Function<Observable<String>, Single<String>>>> entry) {
        printAnswer(String.format(FIRST_TASK_FORMAT, entry.getKey()), String.format(FILE_FORMAT, entry.getKey()), entry.getValue().getFirst());
        printAnswer(String.format(SECOND_TASK_FORMAT, entry.getKey()), String.format(FILE_FORMAT, entry.getKey()), entry.getValue().getSecond());
        return 2;
    }

    private static void printAll() {
        int tasks = ANS.entrySet().stream().mapToInt(Answers::startBoth).sum();
        LOGGER.info(() -> "Done with " + tasks + " tasks");
    }
}
