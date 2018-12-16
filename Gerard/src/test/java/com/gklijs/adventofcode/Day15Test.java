package com.gklijs.adventofcode;

import com.gklijs.adventofcode.day15.Day15;
import com.gklijs.adventofcode.test.TestSchedulerExtension;
import io.reactivex.schedulers.TestScheduler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static com.gklijs.adventofcode.test.TestUtil.testSingle;

class Day15Test {

    private String[] data1 = new String[]{
        "#######",
        "#.G...#",
        "#...EG#",
        "#.#.#G#",
        "#..G#E#",
        "#.....#",
        "#######",
    };

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example1(TestScheduler scheduler) {
        testSingle(scheduler, data1, Day15::lastTurn, "27730");
    }

    private String[] data2 = new String[]{
        "#######",
        "#G..#E#",
        "#E#E.E#",
        "#G.##.#",
        "#...#E#",
        "#...E.#",
        "#######",
    };

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example2(TestScheduler scheduler) {
        testSingle(scheduler, data2, Day15::lastTurn, "36334");
    }

    private String[] data3 = new String[]{
        "#######",
        "#E..EG#",
        "#.#G.E#",
        "#E.##E#",
        "#G..#.#",
        "#..E#.#",
        "#######"
    };

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example3(TestScheduler scheduler) {
        testSingle(scheduler, data3, Day15::lastTurn, "39514");
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example4(TestScheduler scheduler) {
        testSingle(scheduler, data1, Day15::noDeadElf, "4988");
    }

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example5(TestScheduler scheduler) {
        testSingle(scheduler, data3, Day15::noDeadElf, "31284");
    }

    private String[] data4 = new String[]{
        "#######",
        "#E.G#.#",
        "#.#G..#",
        "#G.#.G#",
        "#G..#.#",
        "#...E.#",
        "#######"
    };

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example6(TestScheduler scheduler) {
        testSingle(scheduler, data4, Day15::noDeadElf, "3478");
    }

    private String[] data5 = new String[]{
        "#######",
        "#.E...#",
        "#.#..G#",
        "#.###.#",
        "#E#G#G#",
        "#...#G#",
        "#######"
    };

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example7(TestScheduler scheduler) {
        testSingle(scheduler, data5, Day15::noDeadElf, "6474");
    }

    private String[] data6 = new String[]{
        "#########",
        "#G......#",
        "#.E.#...#",
        "#..##..G#",
        "#...##..#",
        "#...#...#",
        "#.G...G.#",
        "#.....G.#",
        "#########"
    };

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void example8(TestScheduler scheduler) {
        testSingle(scheduler, data6, Day15::noDeadElf, "1140");
    }

    private String[] cc1 = new String[]{
        "####",
        "##E#",
        "#GG#",
        "####"
    };

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void cc1(TestScheduler scheduler) {
        testSingle(scheduler, cc1, Day15::lastTurn, "13400");
    }

    private String[] cc2 = new String[]{
        "#####",
        "#GG##",
        "#.###",
        "#..E#",
        "#.#G#",
        "#.E##",
        "#####"
    };

    @ExtendWith(TestSchedulerExtension.class)
    @Test
    void cc2(TestScheduler scheduler) {
        testSingle(scheduler, cc2, Day15::lastTurn, "13987");
    }
}
