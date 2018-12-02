package nl.openweb.adventofcode2018.ivor.day2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author Ivor
 */
public class Day2Question1 {

    private Map<Integer, Integer> countMap = new HashMap<>();

    public static void main(String... args) {
        var result = new Day2Question1().getChecksum();
        System.out.println("Day2 Q1 Checksum=" + result);
    }

    int getChecksum() {
        countMap.clear();
        countMap.put(2, 0);
        countMap.put(3, 0);

        getLines()
                .map(this::getLetterCounts)
                .flatMap(Set::stream).mapToInt(Integer::intValue)
                .filter(this::filterCounts)
                .forEach( this::addCount);

        return countMap.values().stream()
                .mapToInt(Integer::intValue)
                .reduce(1, Math::multiplyExact);
    }

    private void addCount(int i) {
        if (countMap.containsKey(i)) {
            countMap.put(i, countMap.get(i) + 1);
        }
    }

    private boolean filterCounts(Integer value) {
        return countMap.containsKey(value);
    }

    private Set<Integer> getLetterCounts(String code) {
        var letterCount = new HashMap<Integer, Integer>();

        code.chars().forEach(c ->addToCountMap(letterCount, c));

        // get the counts as a Set
        return new HashSet<>(letterCount.values());
    }

    private void addToCountMap(Map<Integer, Integer> map, Integer letter) {
        if (!map.containsKey(letter)) {
            map.put(letter, 0);
        }
        map.put(letter, map.get(letter) + 1);
    }


    public Stream<String> getLines() {
        var inputFileName = "input_day2.txt";
        try {
            return Files.lines( Paths.get(getClass().getClassLoader()
                    .getResource(inputFileName).toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }
}
