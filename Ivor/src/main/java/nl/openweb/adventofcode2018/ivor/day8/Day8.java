package nl.openweb.adventofcode2018.ivor.day8;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Ivor
 */
public class Day8 {

    public static void main(String... args) {
        Day8 day8 = new Day8();
        int metadataSum = day8.getRootNode().getMetadataSum();
        System.out.println("Q1: " + metadataSum);
        System.out.println("Q2: " + day8.getRootNode().getValue());

    }

    public Node getRootNode() {
        return new Node("0", null, getNumbers());
    }

    public List<Integer> getNumbers() {
        var inputFileName = "input_day8.txt";
        Optional<String> firstLine = Optional.empty();
        try {
            firstLine = Files.lines(Paths.get(getClass().getClassLoader()
                    .getResource(inputFileName).toURI())).findFirst();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return firstLine
                .map(s -> Arrays.stream(s.split("\\s"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .orElseGet(ArrayList::new);
    }
}
