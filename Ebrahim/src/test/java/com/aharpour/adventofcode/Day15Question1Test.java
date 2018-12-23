package com.aharpour.adventofcode;

import com.aharpour.adventofcode.utils.IntPair;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day15Question1Test {

    @Test
    public void givenCase1() {
        IntPair result = new Day15Question1("#######\n" +
                "#.G...#\n" +
                "#...EG#\n" +
                "#.#.#G#\n" +
                "#..G#E#\n" +
                "#.....#\n" +
                "#######\n", false, 0).calculate();
        assertEquals(590, result.getKey());
        assertEquals(47, result.getValue());
    }

    @Test
    public void givenCase2() {
        IntPair result = new Day15Question1("#######\n" +
                "#G..#E#\n" +
                "#E#E.E#\n" +
                "#G.##.#\n" +
                "#...#E#\n" +
                "#...E.#\n" +
                "#######", false, 0).calculate();
        assertEquals(982, result.getKey());
        assertEquals(37, result.getValue());
    }

    @Test
    public void givenCase3() {
        IntPair result = new Day15Question1("#######\n" +
                "#E..EG#\n" +
                "#.#G.E#\n" +
                "#E.##E#\n" +
                "#G..#.#\n" +
                "#..E#.#\n" +
                "#######\n", false, 0).calculate();
        assertEquals(859, result.getKey());
        assertEquals(46, result.getValue());
    }

    @Test
    public void givenCase4() {
        IntPair result = new Day15Question1("#######\n" +
                "#E.G#.#\n" +
                "#.#G..#\n" +
                "#G.#.G#\n" +
                "#G..#.#\n" +
                "#...E.#\n" +
                "#######", false, 0).calculate();
        assertEquals(793, result.getKey());
        assertEquals(35, result.getValue());
    }

    @Test
    public void givenCase5() {
        IntPair result = new Day15Question1("#######\n" +
                "#.E...#\n" +
                "#.#..G#\n" +
                "#.###.#\n" +
                "#E#G#G#\n" +
                "#...#G#\n" +
                "#######\n", false, 0).calculate();

        assertEquals(536, result.getKey());
        assertEquals(54, result.getValue());
    }

    @Test
    public void givenCase6() {
        IntPair result = new Day15Question1("#########\n" +
                "#G......#\n" +
                "#.E.#...#\n" +
                "#..##..G#\n" +
                "#...##..#\n" +
                "#...#...#\n" +
                "#.G...G.#\n" +
                "#.....G.#\n" +
                "#########", false, 0).calculate();
        assertEquals(937, result.getKey());
        assertEquals(20, result.getValue());
    }

}

