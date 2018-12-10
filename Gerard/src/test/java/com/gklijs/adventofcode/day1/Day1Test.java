package com.gklijs.adventofcode.day1;

import com.gklijs.adventofcode.test.TestSchedulerExtension;
import io.reactivex.schedulers.TestScheduler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static com.gklijs.adventofcode.test.TestUtil.testSingle;

class Day1Test {

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example1(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"+1", "+1", "+1"}, Day1::calculateFrequency, 3);
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example2(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"+1", "+1", "-2"}, Day1::calculateFrequency, 0);
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example3(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"-1", "-2", "-3"}, Day1::calculateFrequency, -6);
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example4(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"+1", "-1"}, Day1::firstDoubleFrequency, 0);
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example5(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"+3", "+3", "+4", "-2", "-4"}, Day1::firstDoubleFrequency, 10);
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example6(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"-6", "+3", "+8", "+5", "-6"}, Day1::firstDoubleFrequency, 5);
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example7(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"+7", "+7", "-2", "-7", "-4"}, Day1::firstDoubleFrequency, 14);
    }
}