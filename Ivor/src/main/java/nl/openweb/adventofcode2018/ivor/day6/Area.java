package nl.openweb.adventofcode2018.ivor.day6;

import nl.openweb.adventofcode2018.ivor.day4.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ivor
 */
public class Area {
    private final List<Point> points;


    public Area(List<Point> points) {
        this.points = points;
    }

    public Pair<Point, Point> getArea() {
        Point topLeft = new Point(points.stream().mapToInt(Point::getX).min().getAsInt(),
                points.stream().mapToInt(Point::getY).min().getAsInt());
        Point bottomRight = new Point(points.stream().mapToInt(Point::getX).max().getAsInt(),
                points.stream().mapToInt(Point::getY).max().getAsInt());
        return new Pair<>(topLeft, bottomRight);
    }

    public long getQ2Area() {
        // map point-on-area to sum of distances to points
        Map<Point, Integer> result = new HashMap<>();
        // distance
        Set<Point> pointWithAreaOnEdges = new HashSet<>();

        Pair<Point, Point> area = getArea();
        for (int x = area.getOne().getX(); x <= area.getTwo().getX(); x++) {
            for (int y = area.getOne().getY(); y <= area.getTwo().getY(); y++) {
                Point xy = new Point(x, y);
                int totalDistance = 0;
                for (Point p : points) {
                    int distance = p.getDistance(xy);
                    totalDistance += distance;
                }
                if (totalDistance < 10000 && (xy.getX() == area.getOne().getX() || xy.getX() == area.getTwo().getX() || xy.getY() == area.getOne().getY() || xy.getY() == area.getTwo().getY())) {
                    pointWithAreaOnEdges.add(xy);
                    System.out.println("Found point on edge which is on area " + xy + ", " + totalDistance);
                }
                result.put(xy, totalDistance);
            }
        }

        return result.entrySet().stream()
                .filter(entry -> entry.getValue() < 10000)
                .count();
    }

    public Integer getLargestFiniteArea() {
        // map input-point to all closest points
        Map<Point, List<Point>> result = new HashMap<>();
        // distance
        Set<Point> pointWithAreaOnEdges = new HashSet<>();

        Pair<Point, Point> area = getArea();
        for (int x = area.getOne().getX(); x <= area.getTwo().getX(); x++) {
            for (int y = area.getOne().getY(); y <= area.getTwo().getY(); y++) {
                Point xy = new Point(x, y);
                SortedMap<Integer, List<Point>> pointsBydistance = new TreeMap<>();
                for (Point p : points) {
                    int distance = p.getDistance(xy);
                    putInMap(pointsBydistance, p, distance);
                }

                Map.Entry<Integer, List<Point>> leastDistance = ((TreeMap<Integer, List<Point>>) pointsBydistance).firstEntry();
                if (leastDistance.getValue().size() == 1) {
                    Point inputPoint = leastDistance.getValue().get(0);
//                    System.out.println("Point " + inputPoint + " is closest to " + xy + ": " + leastDistance.getKey());
                    putInMap(result, xy, inputPoint);
                    if (xy.getX() == area.getOne().getX() || xy.getX() == area.getTwo().getX() || xy.getY() == area.getOne().getY() || xy.getY() == area.getTwo().getY()) {
                        pointWithAreaOnEdges.add(inputPoint);
                    }
                }
            }
        }
        System.out.println("Infinite "  + pointWithAreaOnEdges.size() + ": " + pointWithAreaOnEdges);
        Map<Point, Integer> collectedMap = result.entrySet().stream()
                .filter(pointListEntry -> !pointWithAreaOnEdges.contains(pointListEntry.getKey()))
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue().size()));
        System.out.println("Collected " + collectedMap);

        Optional<Map.Entry<Point, List<Point>>> max = result.entrySet().stream()
                .filter(pointListEntry -> !pointWithAreaOnEdges.contains(pointListEntry.getKey()))
                .max((e1, e2) -> e1.getValue().size() > e2.getValue().size() ? 1 : -1);
        max.ifPresent(s -> System.out.println("Result: " + s.getKey() + " with area of " + s.getValue().size()));

        return max.get().getValue().size();
    }

    private <T, S> void  putInMap(Map<T, List<S>> pointsBydistance, S p, T distance) {
        if (!pointsBydistance.containsKey(distance)) {
            pointsBydistance.put(distance, new ArrayList<>());
        }
        pointsBydistance.get(distance).add(p);
    }
}
