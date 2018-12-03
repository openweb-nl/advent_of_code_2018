package nl.openweb.adventofcode2018.ivor.day3;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ivor
 */
public class Day3Question2Test {


    @Test
    public void testIsOverlapping() {
        List<Claim> claims = new Day3Question2().getLines().collect(Collectors.toList());
        assertTrue(claims.get(0).isOverlapping(claims.get(1)));
        assertFalse(claims.get(0).isOverlapping(claims.get(2)));
        assertFalse(claims.get(1).isOverlapping(claims.get(2)));

    }

    @Test
    public void testFindNonOverlappingClaim() {
        Set<Claim> notOverlappingClaims = new Day3Question2().getNotOverlappingClaims();
        assertEquals(1, notOverlappingClaims.size());
        assertEquals(3, notOverlappingClaims.iterator().next().getId());
    }
}
