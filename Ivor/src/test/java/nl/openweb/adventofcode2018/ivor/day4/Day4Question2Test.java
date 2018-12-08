package nl.openweb.adventofcode2018.ivor.day4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ivor
 */
public class Day4Question2Test {


    @Test
    public void test1() {
        Day4Question2 classUnderTest = new Day4Question2();
        int answer = classUnderTest.getAnswer();
        assertEquals(4455, answer);
    }
}
