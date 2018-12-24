package com.aharpour.adventofcode;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day24 {

    private static final Pattern PATTERN = Pattern.compile("(\\d+) units each with (\\d+) hit points (\\((.*)\\) )?with an attack that does (\\d+) ([a-z]+) damage at initiative (\\d+)");
    protected List<Group> groups = new ArrayList<>();
    protected List<Group> immuneSystem = new ArrayList<>();
    protected List<Group> infection = new ArrayList<>();
    private Map<Group, Group> targetingMap;
    private boolean print;

    public Day24(String input, boolean print) {
        this.print = print;
        String[] split = input.split("\\s*\\n\\s*");
        boolean immuneSystem = false;
        for (String item : split) {
            if ("Immune System:".equals(item)) {
                immuneSystem = true;
            } else if ("Infection:".equals(item)) {
                immuneSystem = false;
            } else {
                Matcher matcher = PATTERN.matcher(item);
                if (matcher.find()) {
                    addGroup(matcher, immuneSystem);
                }
            }

        }
    }

    public void setBoost(int boost) {
        immuneSystem.forEach(i -> i.setBoost(boost));
    }


    public void fight() {
        target();
        attack();
    }

    private void attack() {
        groups.stream().sorted(Comparator.comparing(Group::getInitiative).reversed())
                .filter(g -> g.units > 0)
                .filter(g -> targetingMap.get(g) != null)
                .forEach(g -> targetingMap.get(g).attacked(g));
        if (print) {
            System.out.println();
        }
    }

    private void target() {
        targetingMap = new HashMap<>();
        groups.stream().sorted(Comparator.comparing(Group::getEffectivePower).thenComparing(Group::getInitiative).reversed())
                .filter(group -> group.units > 0)
                .forEach(this::selectTarget);
    }

    private void selectTarget(Group group) {
        Map<Integer, List<Group>> map = getEnemies(group).stream()
                .filter(g -> g.units > 0)
                .filter(g -> !targetingMap.values().contains(g))
                .collect(Collectors.groupingBy(g -> g.theoreticalDamage(group)));
        map.keySet().stream().filter(k -> k > 0).max(Integer::compareTo).ifPresent(
                key -> {
                    List<Group> groups = map.get(key);
                    groups.sort(Comparator.comparingInt(Group::getEffectivePower).thenComparingInt(Group::getInitiative).reversed());
                    Group target = groups.get(0);
                    targetingMap.put(group, target);
                }
        );
    }

    private List<Group> getEnemies(Group group) {
        if (group.immuneSystem) {
            return infection;
        } else {
            return immuneSystem;
        }
    }

    private void addGroup(Matcher matcher, boolean immuneSystem) {
        Group group = new Group(matcher, immuneSystem);
        this.groups.add(group);
        if (group.immuneSystem) {
            group.setId(this.immuneSystem.size());
            this.immuneSystem.add(group);
        } else {
            group.setId(this.infection.size());
            this.infection.add(group);
        }
    }

    public void print() {
        if (print) {
            System.out.println("Immune System:");
            immuneSystem.stream()
                    .filter(i -> i.units > 0)
                    .forEach(System.out::println);
            System.out.println("Infection:");
            infection.stream()
                    .filter(i -> i.units > 0)
                    .forEach(System.out::println);
            if (print) {
                System.out.println();
            }
        }
    }

    @Getter
    protected class Group {
        @Setter
        private int id;
        private int units;
        private int hitPoints;
        private int attackPoints;
        private String attackType;
        private Set<String> weaknesses;
        private Set<String> immunities;
        private int initiative;
        @Setter
        private int boost;
        boolean immuneSystem;


        public Group(Matcher matcher, boolean immuneSystem) {
            this.immuneSystem = immuneSystem;
            this.units = Integer.parseInt(matcher.group(1));
            this.hitPoints = Integer.parseInt(matcher.group(2));
            this.attackPoints = Integer.parseInt(matcher.group(5));
            this.attackType = matcher.group(6);
            this.initiative = Integer.parseInt(matcher.group(7));
            parseWeaknessesAndImmunities(matcher.group(4));
        }


        private void parseWeaknessesAndImmunities(String value) {
            if (value != null) {
                String[] split = value.split("\\s*;\\s*");
                for (String item : split) {
                    if (item.startsWith("immune to ")) {
                        this.immunities = valuesAsSet(item.substring("immune to ".length()));
                    } else if (item.startsWith("weak to ")) {
                        this.weaknesses = valuesAsSet(item.substring("weak to ".length()));
                    } else {
                        throw new IllegalArgumentException("Unexpected value: " + item);
                    }
                }
            }

            if (this.immunities == null) {
                this.immunities = Collections.unmodifiableSet(Collections.emptySet());
            }
            if (this.weaknesses == null) {
                this.weaknesses = Collections.unmodifiableSet(Collections.emptySet());
            }


        }

        public int theoreticalDamage(Group attackingGroup) {
            int result = 0;
            String attackType = attackingGroup.attackType;
            if (immunities == null || !this.immunities.contains(attackType)) {
                int effectivePower = attackingGroup.getEffectivePower();
                result = effectivePower * (this.weaknesses.contains(attackType) ? 2 : 1);
            }
            return result;
        }

        protected void attacked(Group attackingGroup) {
            if (attackingGroup.units > 0) {
                int theoreticalDamage = theoreticalDamage(attackingGroup);
                int killedUtils = Math.min(this.units, theoreticalDamage / this.hitPoints);
                if (print) {
                    System.out.println((attackingGroup.immuneSystem ? "Immune System" : "Infection") + " group " +
                            (attackingGroup.id + 1) + " attacks defending group " + (this.id + 1) + ", killing " + killedUtils + " units");
                }
                this.units -= killedUtils;
            } else {
                throw new IllegalArgumentException("Attaching group is dead: " + attackingGroup.initiative);
            }

        }

        protected int getEffectivePower() {
            return units * (attackPoints + boost);
        }

        private Set<String> valuesAsSet(String values) {
            String[] split = values.split("\\s*,\\s*");
            Set<String> result = new HashSet<>();
            Collections.addAll(result, split);
            return Collections.unmodifiableSet(result);
        }

        @Override
        public String toString() {
            return "Group " + (id + 1) + " contains " + units + " units";
        }
    }
}
