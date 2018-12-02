package nl.openweb.adventofcode2018.ivor.day1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * @author Ivor
 */
public class Day1Question2Test {

    @Test
    public void getNewFrequencyTest() {
        Day1Question2 classUnderTest = new Day1Question2();

        var result= classUnderTest.getNewFrequency(3);
        assertEquals(Integer.valueOf(3), result);

        result = classUnderTest.getNewFrequency(5);
        assertEquals(Integer.valueOf(3+5), result);

        result = classUnderTest.getNewFrequency(-15);
        assertEquals(Integer.valueOf(3+5-15), result);
    }

    @Test
    public void testExample() {
        Day1Question2 classUnderTest = new Day1Question2() {
            @Override
            public IntStream getLines() throws IOException {
                return IntStream.of(1, -2, 3, 1, 1, -2, 3);
            }
        };

        OptionalInt firstFrequencyItReachesTwice = classUnderTest.getFirstFrequencyItReachesTwice();
        assertTrue(firstFrequencyItReachesTwice.isPresent());
        assertEquals(2, firstFrequencyItReachesTwice.getAsInt());
    }


}
