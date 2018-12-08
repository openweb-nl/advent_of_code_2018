package nl.openweb.adventofcode2018.ivor.day7;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ivor
 */
public class Day7Test {

    @Test
    public void testCanStart() {
        Step step = new Step("A");
        List<String> finishedSteps = new ArrayList<>();
        assertTrue(step.canStart(finishedSteps));

        Step stepB = new Step("B");
        step.addPrequisite(stepB);
        assertTrue(stepB.canStart(finishedSteps));
        assertFalse(step.canStart(finishedSteps));
        finishedSteps.add("B");
        assertFalse(step.canStart(finishedSteps));

    }

    @Test
    public void testExample() {
        String sequence = new Day7().getPlan().getSequence();
        assertEquals("CABDFE", sequence);
    }


    @Test
    public void testGetTimeTaken() {
        Step stepA = new Step("A");
        CompletedStep cs = new CompletedStep(stepA, 1, 10);
        assertEquals(61, cs.getTimeTaken());
        assertEquals(71, cs.getTimeFinished());
    }

    @Test
    public void testGetTimeTakenB() {
        Step stepB = new Step("B");
        CompletedStep cs = new CompletedStep(stepB, 1, 10);
        assertEquals(62, cs.getTimeTaken());
        assertEquals(72, cs.getTimeFinished());
    }

    @Test
    public void testExample2() {
        Day7 day7 = new Day7();
        assertEquals(15, day7.getPlan().getTimeTaken(new TreeSet<>(Arrays.asList(1,2))));
    }
}
