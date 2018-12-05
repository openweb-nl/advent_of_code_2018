package com.gklijs.adventofcode.day4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gklijs.adventofcode.Utils;
import com.gklijs.adventofcode.utils.Pair;
import io.reactivex.Observable;
import io.reactivex.Single;

public class Day4 {

    private Day4() {
        //prevent instantiation
    }

    public static Single<Pair<Integer, Integer>> bestOpportunity(Observable<String> guardEvents) {
        return guardEvents
            .map(GuardEvent::new)
            .toSortedList()
            .flattenAsObservable(g -> g)
            .reduce(new Pair<>(new HashMap<>(), null), Day4::updateFrequencies)
            .map(Day4::getSleeper)
            .map(Day4::getHour);
    }

    public static Single<Pair<Integer, Integer>> mostAtSameMinute(Observable<String> guardEvents) {
        return guardEvents
            .map(GuardEvent::new)
            .toSortedList()
            .flattenAsObservable(g -> g)
            .reduce(new Pair<>(new HashMap<>(), null), Day4::updateFrequencies)
            .map(Day4::getSleeper2);
    }

    private static Pair<Map<Integer, Map<Integer, Integer>>, GuardEvent> updateFrequencies(Pair<Map<Integer, Map<Integer, Integer>>, GuardEvent> result, GuardEvent guardEvent) {
        List<Integer> sleep = guardEvent.guardEventType.getAsleep(result.getSecond(), guardEvent);
        if (!sleep.isEmpty()) {
            if (result.getFirst().containsKey(guardEvent.id)) {
                Utils.addToFrequencyMap(result.getFirst().get(guardEvent.id), sleep);
            } else {
                result.getFirst().put(guardEvent.id, Utils.toFrequencyMap(sleep));
            }
        }
        result.setSecond(guardEvent);
        return result;
    }

    private static Pair<Integer, Map<Integer, Integer>> getSleeper(Pair<Map<Integer, Map<Integer, Integer>>, GuardEvent> result) {
        int currentId = -1;
        int maxAsleep = -1;
        for (Map.Entry<Integer, Map<Integer, Integer>> idFrequencyMap : result.getFirst().entrySet()) {
            int asleep = idFrequencyMap.getValue().values().stream().reduce(0, (x, y) -> x + y);
            if (asleep > maxAsleep) {
                currentId = idFrequencyMap.getKey();
                maxAsleep = asleep;
            }
        }
        return new Pair<>(currentId, result.getFirst().get(currentId));
    }

    private static Pair<Integer, Integer> getHour(Pair<Integer, Map<Integer, Integer>> result) {
        int currentHour = -1;
        int maxAsleep = -1;
        for (Map.Entry<Integer, Integer> hourFrequency : result.getSecond().entrySet()) {
            if (hourFrequency.getValue() > maxAsleep) {
                currentHour = hourFrequency.getKey();
                maxAsleep = hourFrequency.getValue();
            }
        }
        return new Pair<>(result.getFirst(), currentHour);
    }

    private static Pair<Integer, Integer> getMaxAsleep(Map<Integer, Integer> frequentyMap) {
        int currentMinute = -1;
        int maxAsleep = -1;
        for (Map.Entry<Integer, Integer> hourFrequency : frequentyMap.entrySet()) {
            if (hourFrequency.getValue() > maxAsleep) {
                currentMinute = hourFrequency.getKey();
                maxAsleep = hourFrequency.getValue();
            }
        }
        return new Pair<>(maxAsleep, currentMinute);
    }

    private static Pair<Integer, Integer> getSleeper2(Pair<Map<Integer, Map<Integer, Integer>>, GuardEvent> result) {
        int currentId = -1;
        int currentMinute = -1;
        int maxAsleep = -1;
        for (Map.Entry<Integer, Map<Integer, Integer>> idFrequencyMap : result.getFirst().entrySet()) {
            Pair<Integer, Integer> max = getMaxAsleep(idFrequencyMap.getValue());
            if (max.getFirst() > maxAsleep) {
                currentId = idFrequencyMap.getKey();
                currentMinute = max.getSecond();
                maxAsleep = max.getFirst();
            }
        }
        return new Pair<>(currentId, currentMinute);
    }
}
