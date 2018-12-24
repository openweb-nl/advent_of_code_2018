package com.aharpour.adventofcode;

import org.junit.Assert;
import org.junit.Test;

public class Day24Question2Test {

    @Test
    public void givenCase() {
        int value = Day24Question2.calculate("Immune System:\n" +
                "17 units each with 5390 hit points (weak to radiation, bludgeoning) with an attack that does 4507 fire damage at initiative 2\n" +
                "989 units each with 1274 hit points (immune to fire; weak to bludgeoning, slashing) with an attack that does 25 slashing damage at initiative 3\n" +
                "\n" +
                "Infection:\n" +
                "801 units each with 4706 hit points (weak to radiation) with an attack that does 116 bludgeoning damage at initiative 1\n" +
                "4485 units each with 2961 hit points (immune to radiation; weak to fire, cold) with an attack that does 12 slashing damage at initiative 4");
        Assert.assertEquals(51, value);
    }

    @Test
    public void realCase() {
        int value = Day24Question2.calculate("Immune System:\n" +
                "4555 units each with 9688 hit points (immune to radiation; weak to bludgeoning) with an attack that does 17 radiation damage at initiative 1\n" +
                "2698 units each with 9598 hit points (immune to slashing) with an attack that does 29 slashing damage at initiative 16\n" +
                "4682 units each with 6161 hit points with an attack that does 13 radiation damage at initiative 19\n" +
                "8197 units each with 4985 hit points (weak to cold) with an attack that does 5 cold damage at initiative 18\n" +
                "582 units each with 3649 hit points with an attack that does 46 slashing damage at initiative 13\n" +
                "53 units each with 5147 hit points (immune to bludgeoning, slashing) with an attack that does 828 cold damage at initiative 11\n" +
                "5231 units each with 8051 hit points (weak to radiation) with an attack that does 14 radiation damage at initiative 9\n" +
                "704 units each with 4351 hit points (immune to cold; weak to slashing) with an attack that does 60 radiation damage at initiative 2\n" +
                "326 units each with 9157 hit points (weak to cold, slashing) with an attack that does 261 radiation damage at initiative 6\n" +
                "6980 units each with 3363 hit points (weak to radiation) with an attack that does 4 slashing damage at initiative 4\n" +
                "\n" +
                "Infection:\n" +
                "1994 units each with 48414 hit points (immune to slashing) with an attack that does 46 cold damage at initiative 3\n" +
                "42 units each with 41601 hit points (weak to radiation; immune to fire) with an attack that does 1547 bludgeoning damage at initiative 7\n" +
                "3050 units each with 29546 hit points (immune to fire) with an attack that does 19 fire damage at initiative 10\n" +
                "3825 units each with 5609 hit points (immune to cold; weak to slashing, bludgeoning) with an attack that does 2 bludgeoning damage at initiative 12\n" +
                "37 units each with 30072 hit points with an attack that does 1365 cold damage at initiative 20\n" +
                "189 units each with 49726 hit points (weak to bludgeoning) with an attack that does 514 slashing damage at initiative 5\n" +
                "930 units each with 39623 hit points (weak to radiation) with an attack that does 81 bludgeoning damage at initiative 8\n" +
                "6343 units each with 31638 hit points (immune to slashing) with an attack that does 9 bludgeoning damage at initiative 15\n" +
                "1561 units each with 10633 hit points (weak to radiation, cold) with an attack that does 12 cold damage at initiative 14\n" +
                "3198 units each with 25539 hit points (immune to radiation, fire) with an attack that does 15 bludgeoning damage at initiative 17");
        Assert.assertEquals(4428, value);
    }



}