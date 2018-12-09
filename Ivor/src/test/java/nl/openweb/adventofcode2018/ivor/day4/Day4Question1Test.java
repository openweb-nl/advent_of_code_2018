package nl.openweb.adventofcode2018.ivor.day4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ivor
 */
public class Day4Question1Test {


    @Test
    public void test1() {
        Day4Question1 classUnderTest = new Day4Question1();
        GuardSchedule guardSchedule = classUnderTest.getGuardSchedule();
        assertEquals(2, guardSchedule.getShiftsPerGuard().size());
        assertEquals(2, guardSchedule.getShiftsPerGuard().get(10).size());
        assertEquals(Integer.valueOf(10), guardSchedule.getShiftsPerGuard().get(10).get(0).getGuardId());
        assertEquals(2, guardSchedule.getShiftsPerGuard().get(10).get(0).getSleeps().size());
        assertEquals(3, guardSchedule.getShiftsPerGuard().get(99).size());

        Integer id = guardSchedule.getGuardWithMostMinutesAsleep();
        int answer = id * guardSchedule.getMinuteMostAsleep(id).getOne();
        assertEquals(240, answer);
    }
}
