package nl.openweb.adventofcode2018.ivor.day6;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Ivor
 */
public class D6Q1 {

    public static void main(String... args) {
        System.out.println("Q1: " + new D6Q1().getQ1Answer());
        System.out.println("---------------------------");
        System.out.println("Q2: " + new D6Q1().getQ2Answer());
    }

    long getQ2Answer() {
        List<Point> points = getLines().collect(Collectors.toList());
        Area area = new Area(points);
        return area.getQ2Area();
    }

    Integer getQ1Answer() {
        List<Point> points = getLines().collect(Collectors.toList());
        Area area = new Area(points);
        return area.getLargestFiniteArea();
    }


    public Stream<Point> getLines() {
        var inputFileName = "input_day6.txt";
        try {
            return Files.lines(Paths.get(getClass().getClassLoader()
                    .getResource(inputFileName).toURI())).map(this::getPoint);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }

    private Point getPoint(String line) {
        int indexOfComma = line.indexOf(',');
        try {
            return new Point(
                    Integer.parseInt(line.substring(0, indexOfComma).trim()),
                    Integer.parseInt(line.substring(indexOfComma + 1).trim()));
        } catch (NumberFormatException e) {
            System.out.println("Failed to parse line " + line + "\n " + e.getMessage());
            return null;
        }
    }
}
