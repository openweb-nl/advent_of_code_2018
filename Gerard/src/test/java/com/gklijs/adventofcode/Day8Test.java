package com.gklijs.adventofcode;

import com.gklijs.adventofcode.day8.Day8;
import com.gklijs.adventofcode.test.TestSchedulerExtension;
import io.reactivex.schedulers.TestScheduler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static com.gklijs.adventofcode.test.TestUtil.testSingle;

class Day8Test {

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example1(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2"}, Day8::allMetaData, "138");
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example2(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2"}, Day8::getValue, "66");
    }
}
