package com.gklijs.adventofcode.day20;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.gklijs.adventofcode.utils.Direction;
import com.gklijs.adventofcode.utils.Pair;

class Room {

    private final Pair<Integer, Integer> location;
    private final EnumMap<Direction, Side> sides;

    Room(final Pair<Integer, Integer> location) {
        this.location = location;
        EnumMap<Direction, Side> newSides = new EnumMap<>(Direction.class);
        for (Direction d : Direction.values()) {
            newSides.put(d, Side.UNKNOWN);
        }
        sides = newSides;
    }

    void hasDoor(Direction d) {
        sides.put(d, Side.DOOR);
    }

    List<Pair<Integer, Integer>> reachableRooms() {
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        for (Map.Entry<Direction, Side> entry : sides.entrySet()) {
            if (entry.getValue() == Side.DOOR) {
                result.add(entry.getKey().nextCord(location));
            }
        }
        return result;
    }
}
