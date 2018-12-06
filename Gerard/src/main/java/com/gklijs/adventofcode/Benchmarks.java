package com.gklijs.adventofcode;

import com.gklijs.adventofcode.day1.Day1;
import com.gklijs.adventofcode.day2.Day2;
import com.gklijs.adventofcode.day3.Day3;
import com.gklijs.adventofcode.day4.Day4;
import com.gklijs.adventofcode.day5.Day5;
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

    /* Run on laptop
    11805,463 ±(99.9%) 57,764 ops/s [Average]
    (min, avg, max) = (11657,614, 11805,463, 11946,789), stdev = 77,113
     CI (99.9%): [11747,699, 11863,226] (assumes normal distribution)
     */
    @Benchmark
    public static int bench1of1() {
        return benchIntAnswer("day1.txt", Day1::calculateFrequency);
    }

    /* Run on laptop
    4330,078 ±(99.9%) 1278,156 ops/s [Average]
    (min, avg, max) = (937,131, 4330,078, 5483,925), stdev = 1706,301
    CI (99.9%): [3051,922, 5608,233] (assumes normal distribution)
     */
    @Benchmark
    public static int bench2of1() {
        return benchIntAnswer("day1.txt", Day1::firstDoubleFrequency);
    }

    /* Run on laptop
    4986,058 ±(99.9%) 176,929 ops/s [Average]
    (min, avg, max) = (4609,706, 4986,058, 5264,102), stdev = 236,195
    CI (99.9%): [4809,129, 5162,987] (assumes normal distribution)
     */
    @Benchmark
    public static int bench1of2() {
        return benchIntAnswer("day2.txt", Day2::checksum);
    }

    /* Run on laptop
    18181,520 ±(99.9%) 290,243 ops/s [Average]
    (min, avg, max) = (16930,868, 18181,520, 18561,866), stdev = 387,466
    CI (99.9%): [17891,277, 18471,763] (assumes normal distribution)
     */
    @Benchmark
    public static String bench2of2() {
        return benchStringAnswer("day2.txt", Day2::commonLetters);
    }

    /* Using circle ci
    409.295 ±(99.9%) 9.377 ops/s [Average]
    (min, avg, max) = (378.984, 409.295, 422.954), stdev = 12.518
    CI (99.9%): [399.918, 418.671] (assumes normal distribution)
     */
    @Benchmark
    public static int bench1of3() {
        return benchIntAnswer("day3.txt", Day3::multipleClaims);
    }

    /* Using circle ci
    111.211 ±(99.9%) 10.274 ops/s [Average]
    (min, avg, max) = (92.875, 111.211, 139.175), stdev = 13.716
    CI (99.9%): [100.936, 121.485] (assumes normal distribution)
     */
    @Benchmark
    public static int bench2of3() {
        return benchIntAnswer("day3.txt", Day3::noClaims);
    }

    @Benchmark
    public static Pair<Integer,Integer> bench1of4() {
        return benchPairAnswer("day4.txt", Day4::bestOpportunity);
    }

    @Benchmark
    public static Pair<Integer,Integer> bench2of4() {
        return benchPairAnswer("day4.txt", Day4::mostAtSameMinute);
    }

    @Benchmark
    public static int bench1of5() {
        return benchIntAnswer("day5.txt", Day5::react);
    }

    @Benchmark
    public static int bench2of5() {
        return benchIntAnswer("day5.txt", Day5::reactDeleteReact);
    }
}
