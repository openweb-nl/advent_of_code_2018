package com.gklijs.adventofcode;

import org.openjdk.jmh.annotations.Benchmark;

public class Benchmarks {

    public static void main(String[] args) {
    }


    private static int benchIntAnswer(String fileName, Answers.SingleIntAnswer singleIntAnswer){
        return  singleIntAnswer.getAnswer(Utils.readLines(fileName).toObservable()).blockingGet();
    }

    private static String benchStringAnswer(String fileName, Answers.SingleStringAnswer singleStringAnswer){
        return singleStringAnswer.getAnswer(Utils.readLines(fileName).toObservable()).blockingGet();
    }

    @Benchmark
    public static int bench1of1() {
        return benchIntAnswer("day1question1.txt", Day1Question1::calculateFrequency);
    }

    @Benchmark
    public static int bench2of1() {
        return benchIntAnswer("day1question1.txt", Day1Question2::firstDoubleFrequency);
    }

    @Benchmark
    public static int bench1of2() {
        return benchIntAnswer("day2question1.txt", Day2Question1::checksum);
    }

    @Benchmark
    public static String bench2of2() {
        return benchStringAnswer("day2question1.txt", Day2Question2::commonLetters);
    }
}
