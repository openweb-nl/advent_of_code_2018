package com.gklijs.adventofcode;

import com.gklijs.adventofcode.day1.Day1;
import com.gklijs.adventofcode.day2.Day2;
import com.gklijs.adventofcode.day3.Day3;
import com.gklijs.adventofcode.day4.Day4;
import com.gklijs.adventofcode.day5.Day5;
import com.gklijs.adventofcode.day6.Day6;
import com.gklijs.adventofcode.day7.Day7;
import com.gklijs.adventofcode.day8.Day8;
import com.gklijs.adventofcode.day9.Day9;
import com.gklijs.adventofcode.day9.GapListDay9;
import com.gklijs.adventofcode.utils.Pair;
import org.openjdk.jmh.annotations.Benchmark;

public class Benchmarks {

    public static void main(String[] args) {
    }

    private static int benchIntAnswer(String fileName, Answers.SingleIntAnswer singleIntAnswer) {
        return singleIntAnswer.getAnswer(Utils.readLines(fileName).toObservable()).blockingGet();
    }

    private static String benchStringAnswer(String fileName, Answers.SingleStringAnswer singleStringAnswer) {
        return singleStringAnswer.getAnswer(Utils.readLines(fileName).toObservable()).blockingGet();
    }

    private static Pair<Integer, Integer> benchPairAnswer(String fileName, Answers.SinglePairAnswer singlePairAnswer) {
        return singlePairAnswer.getAnswer(Utils.readLines(fileName).toObservable()).blockingGet();
    }

    private static long benchLongAnswer(String fileName, Answers.SingleLongAnswer singleLongAnswer) {
        return singleLongAnswer.getAnswer(Utils.readLines(fileName).toObservable()).blockingGet();
    }

    /*
    11271.264 ±  616.525  ops/s
     */
    public static int bench1of1() {
        return benchIntAnswer("day1.txt", Day1::calculateFrequency);
    }

    /*
    58.980 ±    1.085  ops/s
     */
    public static int bench2of1() {
        return benchIntAnswer("day1.txt", Day1::firstDoubleFrequency);
    }

    /*
    3196.944 ±   49.840  ops/s
     */
    public static int bench1of2() {
        return benchIntAnswer("day2.txt", Day2::checksum);
    }

    /*
    18775.081 ± 3303.557  ops/s
     */
    public static String bench2of2() {
        return benchStringAnswer("day2.txt", Day2::commonLetters);
    }

    /*
    414.699 ±  11.086  ops/s
     */
    @Benchmark
    public static int bench1of3() {
        return benchIntAnswer("day3.txt", Day3::multipleClaims);
    }

    /*
    115.326 ±   12.167  ops/s
     */
    @Benchmark
    public static int bench2of3() {
        return benchIntAnswer("day3.txt", Day3::noClaims);
    }

    /*
    1177.029 ±   22.148  ops/s
     */
    public static Pair<Integer, Integer> bench1of4() {
        return benchPairAnswer("day4.txt", Day4::bestOpportunity);
    }

    /*
    1229.435 ±   15.518  ops/s
     */
    public static Pair<Integer, Integer> bench2of4() {
        return benchPairAnswer("day4.txt", Day4::mostAtSameMinute);
    }

    /*
    6.372 ±    0.191  ops/s
     */
    public static int bench1of5() {
        return benchIntAnswer("day5.txt", Day5::react);
    }

    /*
    5.042 ±    0.022  ops/s
     */
    public static int bench2of5() {
        return benchIntAnswer("day5.txt", Day5::reactDeleteReact);
    }

    /*
    31.364 ±    0.652  ops/s
     */
    @Benchmark
    public static int bench1of6() {
        return benchIntAnswer("day6.txt", Day6::largestFiniteArea);
    }

    /*
    25.751 ±    0.797  ops/s
     */
    @Benchmark
    public static int bench2of6() {
        return benchIntAnswer("day6.txt", x -> Day6.toAllLessThen(x, 10000));
    }

    /*
    7325.436 ± 181.437  ops/s
     */
    @Benchmark
    public static String bench1of7() {
        return benchStringAnswer("day7.txt", Day7::getOrder);
    }

    /*
    4608.070 ±  293.548  ops/s
     */
    @Benchmark
    public static int bench2of7() {
        return benchIntAnswer("day7.txt", x -> Day7.work(x, 5, 60));
    }

    @Benchmark
    public static int bench1of8() {
        return benchIntAnswer("day8.txt", Day8::allMetaData);
    }

    @Benchmark
    public static int bench2of8() {
        return benchIntAnswer("day8.txt", Day8::getValue);
    }

    @Benchmark
    public static long bench1of9() {
        return benchLongAnswer("day9.txt", Day9::winningScore);
    }

    @Benchmark
    public static long bench2of9() {
        return benchLongAnswer("day9.txt", x -> Day9.winningScore(x, 100));
    }

    @Benchmark
    public static long bench1of9gaplist() {
        return benchLongAnswer("day9.txt", GapListDay9::winningScore);
    }

    @Benchmark
    public static long bench2of9gaplist() {
        return benchLongAnswer("day9.txt", x -> GapListDay9.winningScore(x, 100));
    }
}
