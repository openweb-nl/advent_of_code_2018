package com.gklijs.adventofcode;

import com.gklijs.adventofcode.day9.Day9;
import com.gklijs.adventofcode.test.TestSchedulerExtension;
import io.reactivex.schedulers.TestScheduler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static com.gklijs.adventofcode.test.TestUtil.testSingle;

class Day9Test {

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example0(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"7 players; last marble is worth 25 points"}, Day9::winningScore, 32L);
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example1(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"10 players; last marble is worth 1618 points"}, Day9::winningScore, 8317L);
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example2(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"13 players; last marble is worth 7999 points"}, Day9::winningScore, 146373L);
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example3(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"17 players; last marble is worth 1104 points"}, Day9::winningScore, 2764L);
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example4(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"21 players; last marble is worth 6111 points"}, Day9::winningScore, 54718L);
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example5(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"30 players; last marble is worth 5807 points"}, Day9::winningScore, 37305L);
    }
}
