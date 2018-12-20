package com.gklijs.adventofcode.day20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gklijs.adventofcode.utils.Pair;
import com.gklijs.adventofcode.utils.Triple;
import io.reactivex.Observable;
import io.reactivex.Single;

public class Day20 {

    private Day20() {
        //prevent instantiation
    }

    public static Single<String> mostDoors(Observable<String> input) {
        return input
            .lastOrError()
            .map(Day20::toMap)
            .map(Day20::setFirst)
            .map(Day20::mostDoors);
    }

    public static Single<String> thousandDoors(Observable<String> input) {
        return input
            .lastOrError()
            .map(Day20::toMap)
            .map(Day20::setFirst)
            .map(Day20::thousandDoors);
    }

    private static Map<Pair<Integer, Integer>, Room> init() {
        Map<Pair<Integer, Integer>, Room> start = new HashMap<>();
        Pair<Integer, Integer> origin = new Pair<>(0, 0);
        start.put(origin, new Room(origin));
        return start;
    }

    private static Map<Pair<Integer, Integer>, Room> toMap(String regex) {
        Map<Pair<Integer, Integer>, Room> result = init();
        Set<Pair<Integer, Integer>> current = new HashSet<>();
        current.add(new Pair<>(0, 0));
        List<Set<Pair<Integer, Integer>>> history = new ArrayList<>();
        for (char c : regex.toCharArray()) {
            if (Direction.validDirection(c)) {
                Direction direction = Direction.get(c);
                List<Pair<Integer, Integer>> nexts = new ArrayList<>();
                for (Pair<Integer, Integer> cur : current) {
                    Pair<Integer, Integer> next = direction.nextCord(cur);
                    if (!result.containsKey(next)) {
                        result.put(next, new Room(next));
                    }
                    result.get(next).hasDoor(Direction.opposite(direction));
                    result.get(cur).hasDoor(direction);
                    nexts.add(next);
                }
                current.clear();
                current.addAll(nexts);
            } else if (c == '|') {
                history.get(history.size() - 1).addAll(current);
                current = new HashSet<>(history.get(history.size() - 2));
            } else if (c == '(') {
                history.add(new HashSet<>(current));
                history.add(new HashSet<>());
            } else if (c == ')') {
                current = history.get(history.size() - 1);
                history.remove(history.size() - 1);
                history.remove(history.size() - 1);
            }
        }
        return result;
    }

    private static void loop(Triple<Map<Pair<Integer, Integer>, Room>, Set<Room>, Set<Room>> input) {
        Set<Room> newRooms = new HashSet<>();
        for (Room room : input.getThird()) {
            for (Pair<Integer, Integer> next : room.reachableRooms()) {
                Room nextRoom = input.getFirst().get(next);
                if (!input.getSecond().contains(nextRoom)) {
                    newRooms.add(nextRoom);
                    input.getSecond().add(nextRoom);
                }
            }
        }
        input.getThird().clear();
        input.getThird().addAll(newRooms);
    }

    private static Triple<Map<Pair<Integer, Integer>, Room>, Set<Room>, Set<Room>> setFirst(Map<Pair<Integer, Integer>, Room> map) {
        Set<Room> encounteredRooms = new HashSet<>();
        encounteredRooms.add(map.get(new Pair<>(0, 0)));
        Set<Room> currentRooms = new HashSet<>();
        currentRooms.add(map.get(new Pair<>(0, 0)));
        return new Triple<>(map, encounteredRooms, currentRooms);
    }

    private static String mostDoors(Triple<Map<Pair<Integer, Integer>, Room>, Set<Room>, Set<Room>> input) {
        int counter = 0;
        while (!input.getThird().isEmpty()) {
            loop(input);
            counter++;
        }
        return Integer.toString(counter - 1);
    }

    private static String thousandDoors(Triple<Map<Pair<Integer, Integer>, Room>, Set<Room>, Set<Room>> input) {
        int counter = 0;
        while (!input.getThird().isEmpty() && counter < 999) {
            loop(input);
            counter++;
        }
        return Integer.toString(input.getFirst().size() - input.getSecond().size());
    }
}
