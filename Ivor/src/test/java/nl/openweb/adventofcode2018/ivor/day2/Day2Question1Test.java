package nl.openweb.adventofcode2018.ivor.day2;

import nl.openweb.adventofcode2018.ivor.day2.Day2Question1;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ivor
 */
public class Day2Question1Test {

    @Test
    public void testExample() {
        Day2Question1 classUnderTest = new Day2Question1() {
            @Override
            public Stream<String> getLines() {
                return Arrays.stream(new String[]{"abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab"});
            }
        };

        assertEquals(12, classUnderTest.getChecksum());


    }
}
