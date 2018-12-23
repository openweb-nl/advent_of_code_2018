package com.aharpour.adventofcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day15Question2Test {

    @Test
    public void givenCase1() {
        assertEquals(4988, Day15Question2.calculate("#######\n" +
                "#.G...#\n" +
                "#...EG#\n" +
                "#.#.#G#\n" +
                "#..G#E#\n" +
                "#.....#\n" +
                "#######\n"));
    }

    @Test
    public void givenCase2() {
        assertEquals(29064, Day15Question2.calculate("#######\n" +
                "#G..#E#\n" +
                "#E#E.E#\n" +
                "#G.##.#\n" +
                "#...#E#\n" +
                "#...E.#\n" +
                "#######"));
    }

    @Test
    public void givenCase3() {
        assertEquals(31284, Day15Question2.calculate("#######\n" +
                "#E..EG#\n" +
                "#.#G.E#\n" +
                "#E.##E#\n" +
                "#G..#.#\n" +
                "#..E#.#\n" +
                "#######\n"));
    }

    @Test
    public void givenCase4() {
        assertEquals(3478, Day15Question2.calculate("#######\n" +
                "#E.G#.#\n" +
                "#.#G..#\n" +
                "#G.#.G#\n" +
                "#G..#.#\n" +
                "#...E.#\n" +
                "#######"));
    }

    @Test
    public void givenCase5() {
        assertEquals(6474, Day15Question2.calculate("#######\n" +
                "#.E...#\n" +
                "#.#..G#\n" +
                "#.###.#\n" +
                "#E#G#G#\n" +
                "#...#G#\n" +
                "#######\n"));
    }

    @Test
    public void givenCase6() {
        assertEquals(1140, Day15Question2.calculate("#########\n" +
                "#G......#\n" +
                "#.E.#...#\n" +
                "#..##..G#\n" +
                "#...##..#\n" +
                "#...#...#\n" +
                "#.G...G.#\n" +
                "#.....G.#\n" +
                "#########"));
    }

}