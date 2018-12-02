package com.gklijs.adventofcode;

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

    /*
    11805,463 ±(99.9%) 57,764 ops/s [Average]
    (min, avg, max) = (11657,614, 11805,463, 11946,789), stdev = 77,113
     CI (99.9%): [11747,699, 11863,226] (assumes normal distribution)
     */
    public static int bench1of1() {
        return benchIntAnswer("day1question1.txt", Day1Question1::calculateFrequency);
    }

    /*
    4330,078 ±(99.9%) 1278,156 ops/s [Average]
    (min, avg, max) = (937,131, 4330,078, 5483,925), stdev = 1706,301
    CI (99.9%): [3051,922, 5608,233] (assumes normal distribution)
     */
    public static int bench2of1() {
        return benchIntAnswer("day1question1.txt", Day1Question2::firstDoubleFrequency);
    }

    /*
    4986,058 ±(99.9%) 176,929 ops/s [Average]
    (min, avg, max) = (4609,706, 4986,058, 5264,102), stdev = 236,195
    CI (99.9%): [4809,129, 5162,987] (assumes normal distribution)
     */
    public static int bench1of2() {
        return benchIntAnswer("day2question1.txt", Day2Question1::checksum);
    }

    /*
    3547,472 ±(99.9%) 265,454 ops/s [Average]
    (min, avg, max) = (3214,122, 3547,472, 4099,607), stdev = 354,373
    CI (99.9%): [3282,018, 3812,926] (assumes normal distribution)
     */
    public static String bench2of2() {
        return benchStringAnswer("day2question1.txt", Day2Question2::commonLetters);
    }
}
