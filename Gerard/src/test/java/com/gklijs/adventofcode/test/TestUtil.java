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

    private static String[] input;

    private TestUtil() {
        //prevent instantiation
    }

    public static <T> void testSingle(TestScheduler scheduler, String[] input, Function<Observable<String>, Single<T>> function, T expected) throws Exception {
        TestUtil.input = input;
        var result = new ArrayList<T>();
        function.apply((Observable.fromArray(input)))
            .doOnSuccess(result::add)
            .subscribe();
        scheduler.advanceTimeBy(2, TimeUnit.SECONDS);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(expected, result.get(0));
    }
}
