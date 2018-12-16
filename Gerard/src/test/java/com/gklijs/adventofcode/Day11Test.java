package com.gklijs.adventofcode;

import com.gklijs.adventofcode.day11.Day11;
import com.gklijs.adventofcode.test.TestSchedulerExtension;
import io.reactivex.schedulers.TestScheduler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static com.gklijs.adventofcode.test.TestUtil.testSingle;

class Day11Test {

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example1(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"18"}, Day11::sizeThree, "33,45");
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example2(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"42"}, Day11::sizeThree, "21,61");
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example3(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"18"}, Day11::sizeVariable, "90,269,16");
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example4(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"42"}, Day11::sizeVariable, "232,251,12");
    }
}
