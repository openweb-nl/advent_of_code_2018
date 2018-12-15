package com.gklijs.adventofcode;

import com.gklijs.adventofcode.day14.Day14;
import com.gklijs.adventofcode.test.TestSchedulerExtension;
import io.reactivex.schedulers.TestScheduler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static com.gklijs.adventofcode.test.TestUtil.testSingle;

class Day14Test {

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example1(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"9"}, Day14::tenAfter, "5158916779");
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example2(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"5"}, Day14::tenAfter, "0124515891");
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example3(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"18"}, Day14::tenAfter, "9251071085");
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example4(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"2018"}, Day14::tenAfter, "5941429882");
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example5(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"51589"}, Day14::doTill, 9);
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example6(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"01245"}, Day14::doTill, 5);
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example7(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"92510"}, Day14::doTill, 18);
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example8(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"59414"}, Day14::doTill, 2018);
    }
}
