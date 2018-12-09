package nl.openweb.adventofcode2018.ivor.day8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ivor
 */
public class Day8Test {


    @Test
    public void testExample() {
        Day8 day8 = new Day8();
        int metadataSum = day8.getRootNode().getMetadataSum();
        assertEquals(138, metadataSum);
    }
}
