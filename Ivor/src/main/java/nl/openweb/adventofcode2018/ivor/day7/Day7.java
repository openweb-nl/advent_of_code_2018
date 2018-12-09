package nl.openweb.adventofcode2018.ivor.day7;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 * @author Ivor
 */
public class Day7 {

    public Stream<String> getLines() {
        var inputFileName = "input_day7.txt";
        try {
            return Files.lines( Paths.get(getClass().getClassLoader()
                    .getResource(inputFileName).toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }

    Plan getPlan() {
        Plan plan = new Plan();
        getLines().forEach(plan::addLine);

        return plan;
    }

    public static void main(String... args) {
        Plan plan = new Day7().getPlan();
        System.out.println("Q1: " + plan.getSequence());
        System.out.println("Q2: " + plan.getTimeTaken(new TreeSet<>(Arrays.asList(1,2,3,4,5))));

    }
}
