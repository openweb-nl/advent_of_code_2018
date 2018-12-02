package com.gklijs.adventofcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import com.gklijs.adventofcode.test.TestSchedulerExtension;
import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class Day2Question1Test {

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example1(TestScheduler scheduler) {
        var result = new ArrayList<Integer>();
        Day2Question1.checksum(Observable.fromIterable(Arrays.asList("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab")))
            .doOnSuccess(result::add)
            .subscribe();
        scheduler.advanceTimeBy(2, TimeUnit.SECONDS);
        assertIterableEquals(Collections.singletonList(12), result);
    }
}