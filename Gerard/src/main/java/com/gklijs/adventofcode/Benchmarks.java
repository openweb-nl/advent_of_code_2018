package com.gklijs.adventofcode;

import java.util.function.Function;

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
import com.gklijs.adventofcode.day2.Day2;
import com.gklijs.adventofcode.day3.Day3;
import com.gklijs.adventofcode.day4.Day4;
import com.gklijs.adventofcode.day5.Day5;
import com.gklijs.adventofcode.day6.Day6;
import com.gklijs.adventofcode.day7.Day7;
import com.gklijs.adventofcode.day8.Day8;
import com.gklijs.adventofcode.day9.Day9;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.openjdk.jmh.annotations.Benchmark;

public class Benchmarks {

    public static void main(String[] args) {
    }

    private static String benchAnswer(String fileName, Function<Observable<String>, Single<String>> function) {
        return function.apply(Utils.readLines(fileName).toObservable()).blockingGet();
    }

    /*
    11271.264 ±  616.525  ops/s
     */
    public static String bench1of1() {
        return benchAnswer("day1.txt", Day1::calculateFrequency);
    }

    /*
    58.980 ±    1.085  ops/s
     */
    public static String bench2of1() {
        return benchAnswer("day1.txt", Day1::firstDoubleFrequency);
    }

    /*
    3196.944 ±   49.840  ops/s
     */
    public static String bench1of2() {
        return benchAnswer("day2.txt", Day2::checksum);
    }

    /*
    18775.081 ± 3303.557  ops/s
     */
    public static String bench2of2() {
        return benchAnswer("day2.txt", Day2::commonLetters);
    }

    /*
    219.603 ±  36.755  ops/s
     */
    public static String bench1of3() {
        return benchAnswer("day3.txt", Day3::multipleClaims);
    }

    /*
    111.062 ±   5.080  ops/s
     */
    public static String bench2of3() {
        return benchAnswer("day3.txt", Day3::noClaims);
    }

    /*
    1177.029 ±   22.148  ops/s
     */
    public static String bench1of4() {
        return benchAnswer("day4.txt", Day4::bestOpportunity);
    }

    /*
    1229.435 ±   15.518  ops/s
     */
    public static String bench2of4() {
        return benchAnswer("day4.txt", Day4::mostAtSameMinute);
    }

    /*
    6.372 ±    0.191  ops/s
     */
    public static String bench1of5() {
        return benchAnswer("day5.txt", Day5::react);
    }

    /*
    5.042 ±    0.022  ops/s
     */
    public static String bench2of5() {
        return benchAnswer("day5.txt", Day5::reactDeleteReact);
    }

    /*
    19.026 ±   4.813  ops/s
     */
    public static String bench1of6() {
        return benchAnswer("day6.txt", Day6::largestFiniteArea);
    }

    /*
    11.050 ±   0.910  ops/s
     */
    public static String bench2of6() {
        return benchAnswer("day6.txt", x -> Day6.toAllLessThen(x, 10000));
    }

    /*
    6353.701 ± 368.702  ops/s
     */
    public static String bench1of7() {
        return benchAnswer("day7.txt", Day7::getOrder);
    }

    /*
    3769.542 ± 282.248  ops/s
     */
    public static String bench2of7() {
        return benchAnswer("day7.txt", x -> Day7.work(x, 5, 60));
    }

    /*
    457.389 ±   4.543  ops/s
     */
    public static String bench1of8() {
        return benchAnswer("day8.txt", Day8::allMetaData);
    }

    /*
    506.008 ±   5.322  ops/s
     */
    public static String bench2of8() {
        return benchAnswer("day8.txt", Day8::getValue);
    }

    /*
    2379.998 ±  12.818  ops/s
     */
    public static String bench1of9() {
        return benchAnswer("day9.txt", Day9::winningScore);
    }

    /*
    32.629 ±   5.909  ops/s
     */
    public static String bench2of9() {
        return benchAnswer("day9.txt", x -> Day9.winningScore(x, 100));
    }

    /*
    136.157 ± 0.914  ops/s
     */
    public static String bench1of10() {
        return benchAnswer("day10.txt", Day10::displayStars);
    }

    /*
    136.287 ± 1.863  ops/s
     */
    public static String bench2of10() {
        return benchAnswer("day10.txt", Day10::stepsNeeded);
    }

    /*
    400.856 ± 4.034  ops/s
     */
    public static String bench1of11() {
        return benchAnswer("day11.txt", Day11::sizeThree);
    }

    /*
    13.936 ± 0.130  ops/s
     */
    public static String bench2of11() {
        return benchAnswer("day11.txt", Day11::sizeVariable);
    }

    /*
    15685.785 ± 621.429  ops/s
     */
    public static String bench1of12() {
        return benchAnswer("day12.txt", Day12::plantIndex);
    }

    /*
    3484.004 ± 210.209  ops/s
     */
    public static String bench2of12() {
        return benchAnswer("day12.txt", Day12::plantIndexTwo);
    }

    /*
    969.055 ± 5.631  ops/s
     */
    public static String bench1of13() {
        return benchAnswer("day13.txt", Day13::firstCrash);
    }

    /*
    165.251 ± 1.759  ops/s
     */
    public static String bench2of13() {
        return benchAnswer("day13.txt", Day13::lastCard);
    }

    /*
    148.574 ± 0.703  ops/s
     */
    public static String bench1of14() {
        return benchAnswer("day14.txt", Day14::tenAfter);
    }

    /*
    3.186 ± 0.021  ops/s
     */
    public static String bench2of14() {
        return benchAnswer("day14.txt", Day14::doTill);
    }

    /*
    5.255 ±  0.063  ops/s
     */
    public static String bench1of15() {
        return benchAnswer("day15.txt", Day15::lastTurn);
    }

    /*
    1.749 ±  0.030  ops/s
     */
    public static String bench2of15() {
        return benchAnswer("day15.txt", Day15::noDeadElf);
    }

    /*
    697.277 ±  9.677  ops/s
     */
    public static String bench1of16() {
        return benchAnswer("day16.txt", Day16::threeOrMoreMatches);
    }

    /*
    668.045 ± 14.380  ops/s
     */
    public static String bench2of16() {
        return benchAnswer("day16.txt", Day16::getResult);
    }

    @Benchmark
    public static String bench1of17() {
        return benchAnswer("day17.txt", Day17::waterTotal);
    }

    @Benchmark
    public static String bench2of17() {
        return benchAnswer("day17.txt", Day17::waterRetained);
    }

    @Benchmark
    public static String bench1of18() {
        return benchAnswer("day18.txt", Day18::afterTen);
    }

    @Benchmark
    public static String bench2of18() {
        return benchAnswer("day18.txt", Day18::afterBillion);
    }
}
