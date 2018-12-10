package com.gklijs.adventofcode.day2;

import com.gklijs.adventofcode.test.TestSchedulerExtension;
import io.reactivex.schedulers.TestScheduler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static com.gklijs.adventofcode.test.TestUtil.testSingle;

class Day2Test {

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example1(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab"}, Day2::checksum, 12);
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example2(TestScheduler scheduler) {
        testSingle(scheduler, new String[]{"abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz"}, Day2::commonLetters, "fgij");
    }
}