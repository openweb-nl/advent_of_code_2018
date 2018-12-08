package com.gklijs.adventofcode.test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.TestScheduler;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestUtil {

    private TestUtil() {
        //prevent instantiation
    }

    public static <T> void testSingle(TestScheduler scheduler, String[] input, Function<Observable<String>, Single<T>> function, T expected) {
        var result = new ArrayList<T>();
        try {
            function.apply((Observable.fromArray(input)))
                .doOnSuccess(result::add)
                .timeout(2, TimeUnit.SECONDS)
                .subscribe();
        } catch (Exception e) {
            e.printStackTrace();
        }
        scheduler.advanceTimeBy(2, TimeUnit.SECONDS);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(expected, result.get(0));
    }
}
