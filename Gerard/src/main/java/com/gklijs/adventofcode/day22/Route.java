package com.gklijs.adventofcode.day22;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gklijs.adventofcode.utils.Direction;
import com.gklijs.adventofcode.utils.Pair;

class Route {

    private final int[][] map;
    private final int time;
    private final Equipment equipment;
    private final Pair<Integer, Integer> currentLocation;

    Route(final int[][] map, final int time, final Equipment equipment, final Pair<Integer, Integer> currentLocation) {
        this.map = map;
        this.time = time;
        this.equipment = equipment;
        this.currentLocation = currentLocation;
    }

    private boolean validNext(Pair<Integer, Integer> nextLocation) {
        if (nextLocation.getFirst() < 0 || nextLocation.getSecond() < 0) {
            return false;
        }
        return nextLocation.getFirst() < map[0].length && nextLocation.getSecond() < map.length;
    }

    List<Route> nextRoutes() {
        List<Route> routes = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            Pair<Integer, Integer> nextLocation = direction.nextCord(currentLocation);
            if (!validNext(nextLocation)) {
                continue;
            }
            int nextType = map[nextLocation.getSecond()][nextLocation.getFirst()];
            if (equipment.isValid(nextType)) {
                routes.add(new Route(map, time + 1, equipment, nextLocation));
            } else {
                for (Equipment newEquipment : equipment.others()) {
                    routes.add(new Route(map, time + 8, newEquipment, nextLocation));
                }
            }
        }
        return routes;
    }

    Optional<Integer> rescued(final Pair<Integer, Integer> target) {
        if (currentLocation.equals(target)) {
            return Optional.of(equipment == Equipment.TORCH ? time : time + 7);
        } else {
            return Optional.empty();
        }
    }

    int getTime() {
        return time;
    }

    Pair<Integer, Integer> getCurrentLocation() {
        return currentLocation;
    }

    Equipment getEquipment() {
        return equipment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Route) {
            Route other = (Route) o;
            return currentLocation.equals(other.currentLocation) && time == other.time && equipment == other.equipment;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + currentLocation.hashCode();
        result = 31 * result + Integer.hashCode(time);
        result = 31 * result + equipment.hashCode();
        return result;
    }
}
