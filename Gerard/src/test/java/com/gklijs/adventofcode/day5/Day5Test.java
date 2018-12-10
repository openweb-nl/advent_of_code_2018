package com.gklijs.adventofcode.day5;

import com.gklijs.adventofcode.test.TestSchedulerExtension;
import io.reactivex.schedulers.TestScheduler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static com.gklijs.adventofcode.test.TestUtil.testSingle;

class Day5Test {

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example1(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"dabAcCaCBAcCcaDA"}, Day5::react, 10);
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example2(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"dabAcCaCBAcCcaDA"}, Day5::reactDeleteReact, 4);
    }
}
