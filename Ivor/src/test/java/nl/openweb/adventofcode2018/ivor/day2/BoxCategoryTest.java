package nl.openweb.adventofcode2018.ivor.day2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ivor
 */
public class BoxCategoryTest {

    @Test
    public void test1() {
        // first should always be accepted
        BoxCategory category = new BoxCategory("abc");

        // second should be tested for max one difference
        assertFalse(category.tryBox("foo"));
        assertFalse(category.tryBox("aoo"));

        assertTrue(category.tryBox("abd")); // category now contains abc and abd
        assertEquals(2, category.getIndexOfVariableLetter());

        // after the second (with only one difference) is accepted, then only codes with difference on same index are allowed
        assertFalse(category.tryBox("bbc"));
        assertEquals(2, category.getIndexOfVariableLetter());
        assertFalse(category.tryBox("bbd"));
        assertEquals(2, category.getIndexOfVariableLetter());
        assertTrue(category.tryBox("abe")); // category now contains abc, abd and abe
        assertEquals(2, category.getIndexOfVariableLetter());

        assertEquals("abc", category.getCodes().get(0));
        assertEquals("abd", category.getCodes().get(1));
        assertEquals("abe", category.getCodes().get(2));

        assertEquals("ab", category.getCommonLetters());

    }
}
