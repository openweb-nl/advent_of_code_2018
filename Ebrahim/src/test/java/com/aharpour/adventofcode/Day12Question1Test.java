package com.aharpour.adventofcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day12Question1Test {

    private Day12Question1 question = new Day12Question1(20);

    @Test
    public void givenCases() {
        assertEquals(325, question.calculate("initial state: #..#.#..##......###...###\n" +
                "\n" +
                "...## => #\n" +
                "..#.. => #\n" +
                ".#... => #\n" +
                ".#.#. => #\n" +
                ".#.## => #\n" +
                ".##.. => #\n" +
                ".#### => #\n" +
                "#.#.# => #\n" +
                "#.### => #\n" +
                "##.#. => #\n" +
                "##.## => #\n" +
                "###.. => #\n" +
                "###.# => #\n" +
                "####. => #"));
    }
    @Test
    public void realCase() {
        assertEquals(3248, question.calculate("initial state: ##..##....#.#.####........##.#.#####.##..#.#..#.#...##.#####.###.##...#....##....#..###.#...#.#.#.#\n" +
                "\n" +
                "##.#. => .\n" +
                "##.## => .\n" +
                "#..## => .\n" +
                "#.#.# => .\n" +
                "..#.. => #\n" +
                "#.##. => .\n" +
                "##... => #\n" +
                ".#..# => .\n" +
                "#.### => .\n" +
                "..... => .\n" +
                "...#. => #\n" +
                "#..#. => #\n" +
                "###.. => #\n" +
                ".#... => #\n" +
                "###.# => #\n" +
                "####. => .\n" +
                ".##.# => #\n" +
                "#.#.. => #\n" +
                ".###. => #\n" +
                ".#.## => .\n" +
                "##### => #\n" +
                "....# => .\n" +
                ".#### => .\n" +
                ".##.. => #\n" +
                "##..# => .\n" +
                "#...# => .\n" +
                "..### => #\n" +
                "...## => .\n" +
                "#.... => .\n" +
                "..##. => .\n" +
                ".#.#. => #\n" +
                "..#.# => #"));
    }
}