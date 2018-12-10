package com.gklijs.adventofcode.day6;

import com.gklijs.adventofcode.test.TestSchedulerExtension;
import io.reactivex.schedulers.TestScheduler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static com.gklijs.adventofcode.test.TestUtil.testSingle;

class Day6Test {

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example1(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"1, 1" , "1, 6" , "8, 3" , "3, 4" , "5, 5" , "8, 9"}, Day6::largestFiniteArea, 17);
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example2(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"1, 1" , "1, 6" , "8, 3" , "3, 4" , "5, 5" , "8, 9"}, x -> Day6.toAllLessThen(x, 32), 16);
    }
}
