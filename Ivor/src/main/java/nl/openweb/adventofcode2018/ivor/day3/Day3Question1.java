package nl.openweb.adventofcode2018.ivor.day3;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author Ivor
 */
public class Day3Question1 {

    public static void main(String... args) {
        long answer = new Day3Question1().getMultipleClaimedArea();
        System.out.println("Answer: " + answer);
    }

    long getMultipleClaimedArea() {
        Map<Point, Integer> claimsOnSquareInches = new HashMap<>();
        getLines()
                .forEach(claim -> addClaimToMap(claimsOnSquareInches, claim));
        return claimsOnSquareInches.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .count();
    }

    private void addClaimToMap(Map<Point, Integer> claimsOnSquareInches, Claim claim) {
        claim.getArea().forEach(point -> addPointToMap(claimsOnSquareInches, claim.getId(), point));
    }

    private void addPointToMap(Map<Point, Integer> claimsOnSquareInches, int id, Point point) {
        if (!claimsOnSquareInches.containsKey(point)) {
            claimsOnSquareInches.put(point, 0);
        }
        claimsOnSquareInches.put(point, claimsOnSquareInches.get(point) + 1);
    }

    Claim toClaim(String line) {
        String REGEX = "#([\\d]+) @ ([\\d]+),([\\d]+): ([\\d]+)x([\\d]+)";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches() && matcher.groupCount() == 5) {
            Claim claim = new Claim();
            claim.setId(Integer.parseInt(matcher.group(1)));
            claim.setLeft(Integer.parseInt(matcher.group(2)));
            claim.setTop(Integer.parseInt(matcher.group(3)));
            claim.setWidth(Integer.parseInt(matcher.group(4)));
            claim.setHeight(Integer.parseInt(matcher.group(5)));
            return claim;
        }
        throw new IllegalStateException("Failed to parse input: " + line + "(" + matcher.matches() + ", " + matcher.groupCount() + ")");
    }

    public Stream<Claim> getLines() {
        var inputFileName = "input_day3.txt";
        try {
            return Files.lines( Paths.get(getClass().getClassLoader()
                    .getResource(inputFileName).toURI())).map(this::toClaim);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }

}
