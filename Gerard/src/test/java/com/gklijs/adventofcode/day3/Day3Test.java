package com.gklijs.adventofcode.day3;

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

class Day3Test {

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example1(TestScheduler scheduler) {
        var result = new ArrayList<Integer>();
        Day3.multipleClaims(Observable.fromIterable(Arrays.asList("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2")))
            .doOnSuccess(result::add)
            .subscribe();
        scheduler.advanceTimeBy(2, TimeUnit.SECONDS);
        assertIterableEquals(Collections.singletonList(4), result);
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example2(TestScheduler scheduler) {
        var result = new ArrayList<Integer>();
        Day3.noClaims(Observable.fromIterable(Arrays.asList("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2")))
            .doOnSuccess(result::add)
            .subscribe();
        scheduler.advanceTimeBy(2, TimeUnit.SECONDS);
        assertIterableEquals(Collections.singletonList(3), result);
    }
}