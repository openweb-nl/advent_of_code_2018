package com.aharpour.adventofcode;

import com.aharpour.adventofcode.utils.geometry.FourDPoint;

import java.util.*;

public class Day25 {

    private Map<FourDPoint, FourDPoint> points = new HashMap<>();
    private Set<FourDPoint> alreadyInConstellation = new HashSet<>();
    protected List<Set<FourDPoint>> constellations = new ArrayList<>();

    public Day25(String input) {
        parseInput(input);
    }

    public void calculateConstellations() {
        points.keySet()
                .stream()
                .filter(p -> !alreadyInConstellation.contains(p))
                .forEach(this::process);

    }

    private void process(FourDPoint point) {
        Set<FourDPoint> constellation = new HashSet<>();
        processPoint(constellation, point);
        constellations.add(constellation);
    }

    private void processPoint(Set<FourDPoint> constellation, FourDPoint point) {
        if (!constellation.contains(point)) {
            constellation.add(point);
            alreadyInConstellation.add(point);
            List<FourDPoint> neighbours = getNeighbours(point);
            for (FourDPoint neighbour : neighbours) {
               processPoint(constellation, neighbour);
            }
        }
    }

    private List<FourDPoint> getNeighbours(FourDPoint point) {
        List<FourDPoint> neighbours = new ArrayList<>();
        int px = point.getX();
        int py = point.getY();
        int pz = point.getZ();
        int pu = point.getU();
        for (int x = px - 3; x <= px + 3; x++) {
            for (int y = py - 3; y <= py + 3; y++) {
                for (int z = pz - 3; z <= pz + 3; z++) {
                    for (int u = pu - 3; u <= pu + 3; u++) {
                        if (x != px || y != py || z != pz || u != pu) {
                            FourDPoint newPoint = new FourDPoint(x, y, z, u);
                            if (points.containsKey(newPoint) && point.manhattanDistance(newPoint) <= 3) {
                                neighbours.add(points.get(newPoint));
                            }

                        }
                    }
                }
            }
        }
        return neighbours;
    }

    private void parseInput(String input) {
        String[] lines = input.split("\\s*\\n\\s*");
        for (String line : lines) {
            addToPoints(line);
        }
    }

    private void addToPoints(String line) {
        String[] s = line.split(",");
        FourDPoint p = new FourDPoint(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]));
        points.put(p, p);
    }
}
