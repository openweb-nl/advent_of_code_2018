package nl.openweb.adventofcode2018.ivor.day1;

import java.io.IOException;
import java.util.HashSet;
import java.util.OptionalInt;
import java.util.Set;

/**
 * @author Ivor
 */
public class Day1Question2 extends Day1Question1 {
    private final Set<Integer> frequencies = new HashSet<>();
    private Integer currentFrequency = 0;

    /**
     * Find first frequency it reaches twice
     *
     * @param args not used
     */
    public static void main(String... args) {
        Integer firstFrequencyItReachesTwice = new Day1Question2().getFirstFrequencyItReachesTwiceLoopingLines();
        printResultMessage(firstFrequencyItReachesTwice);
    }

    private static void printResultMessage(Integer frequency) {
        System.out.println("Found frequency " + frequency + " to be the first frequency to be reached twice");
    }

    Integer getFirstFrequencyItReachesTwiceLoopingLines() {
        OptionalInt result = OptionalInt.empty();
        long count = 1;
        while(result.isEmpty()) {
            result = getFirstFrequencyItReachesTwice();
            System.out.println("Loop " + count++ + " result: " + result);
        }
        return result.getAsInt();
    }

    OptionalInt getFirstFrequencyItReachesTwice() {
        try {
            return getLines()
                    .map(this::getNewFrequency)
                    .filter(this::isFrequencyReacedTwice)
                    .findFirst();
        } catch (IOException e) {
            e.printStackTrace();
            return OptionalInt.empty();
        }
    }

    Integer getNewFrequency(Integer addition) {
        currentFrequency = currentFrequency + addition;
        System.out.println("current frequency " + currentFrequency);
        return currentFrequency;
    }

    private boolean isFrequencyReacedTwice(Integer frequency) {
        return !frequencies.add(frequency);
    }

}