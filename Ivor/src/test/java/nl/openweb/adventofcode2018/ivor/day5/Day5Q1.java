package nl.openweb.adventofcode2018.ivor.day5;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * @author Ivor
 */
public class Day5Q1 {

    @Test
    public void testExample() {
        Optional<String> output = new Day5Question1().replaceOpposites("dabAcCaCBAcCcaDA");
        assertEquals("dabCBAcaDA", output.get());
        assertEquals(10, output.get().length());
    }
}
