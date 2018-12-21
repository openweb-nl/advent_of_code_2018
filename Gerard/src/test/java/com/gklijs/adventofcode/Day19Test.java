package com.gklijs.adventofcode;

import com.gklijs.adventofcode.day19day21.Day19;
import com.gklijs.adventofcode.test.TestSchedulerExtension;
import io.reactivex.schedulers.TestScheduler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static com.gklijs.adventofcode.test.TestUtil.testSingle;

class Day19Test {

    String[] data = new String[]{
        "#ip 0",
        "seti 5 0 1",
        "seti 6 0 2",
        "addi 0 1 0",
        "addr 1 2 3",
        "setr 1 0 0",
        "seti 8 0 4",
        "seti 9 0 5"
    };

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example1(TestScheduler scheduler) {
        testSingle(scheduler, data, Day19::first, "7");
    }
}
