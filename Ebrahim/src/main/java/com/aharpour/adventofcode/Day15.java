package com.aharpour.adventofcode;

import com.aharpour.adventofcode.utils.graph.AbstractGraph;
import com.aharpour.adventofcode.utils.graph.WeightedGraph;
import com.aharpour.adventofcode.utils.graph.utils.MultiShortestPathCalculator;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

public class Day15 {

    public static final String Y = "y";
    public static final String X = "x";
    private final int initialHitPoint;
    private final int attackPower;
    private final int boost;
    private final WeightedGraph graph = new WeightedGraph();
    protected List<Creature> elves = new ArrayList<>();
    protected List<Creature> goblins = new ArrayList<>();
    protected List<Creature> creatures = new ArrayList<>();
    private MultiShortestPathCalculator calculator = new MultiShortestPathCalculator(graph);

    public Day15(int initialHitPoint, int attackPower, String map, int boost) {
        this.initialHitPoint = initialHitPoint;
        this.attackPower = attackPower;
        this.boost = boost;
        parseInput(map);

    }

    protected void round() throws NoMoreEnemiesException {
        sortCreatures();
        for (Creature creature : creatures) {
            if (!creature.isDead()) {
                List<Option> options = getOptions(creature);
                if (!options.isEmpty()) {
                    Option option = options.get(0);
                    if (option.length > 2) {
                        moveCreature(creature, option.node);
                    } else if (option.length == 2) {
                        moveCreature(creature, option.node);
                        goOnAssault(creature, getOptions(creature));
                    } else {
                        goOnAssault(creature, options);
                    }
                }
            }
        }
        removeDeadOnes();
    }

    private void goOnAssault(Creature creature, List<Option> options) {
        Map<Integer, List<Creature>> enemies = getNeighbouringEnemies(creature).stream().collect(Collectors.groupingBy(Creature::getHitPoint));
        enemies.keySet().stream().min(Comparator.comparing(Integer::intValue)).ifPresent(
                i -> enemies.get(i).stream().min(Comparator.comparing((Creature c) -> c.getNode().getAttribute(Y)).thenComparing(c -> c.getNode().getAttribute(X))).ifPresent(
                        e -> e.hit(creature.attackingPower)
                )
        );

    }

    protected void removeDeadOnes() {
        removeDeadOnes(this.creatures);
        removeDeadOnes(this.goblins);
        removeDeadOnes(this.elves);

    }

    private void removeDeadOnes(List<Creature> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            Creature creature = list.get(i);
            if (creature.isDead()) {
                list.remove(i);
            }
        }
    }

    private List<Creature> getNeighbouringEnemies(Creature creature) {
        Set<AbstractGraph.Node> neighbours = getNeighbouringNodes(creature.node);
        return getOtherTypeCreatures(creature).stream()
                .filter(c -> neighbours.contains(c.node))
                .filter(c -> !c.isDead())
                .collect(Collectors.toList());
    }

    private Set<AbstractGraph.Node> getNeighbouringNodes(AbstractGraph.Node node) {
        return node.getTo().stream().map(AbstractGraph.Edge::getTo).collect(Collectors.toSet());
    }

    protected List<Option> getOptions(Creature creature) throws NoMoreEnemiesException {
        List<Option> result;

        List<Creature> neighbouringEnemies = getNeighbouringEnemies(creature);
        if (neighbouringEnemies.isEmpty()) {
            List<Creature> otherCreatures = getOtherTypeCreatures(creature);
            List<AbstractGraph.Node> to = otherCreatures.stream()
                    .filter(c -> !c.isDead())
                    .map(Creature::getNode).collect(Collectors.toList());
            if (to.isEmpty()) {
                throw new NoMoreEnemiesException();
            }
            result = getNeighbouringNodes(creature.getNode()).stream()
                    .filter(n -> !n.isBlocked())
                    .map(n -> {
                        Integer distance = calculator.getPaths(n, to);
                        int length = distance > 0 ? distance + 1 : -1;
                        return new Option(n, length, null);
                    })
                    .filter(o -> o.length > 0)
                    .collect(Collectors.toList());
            int min = result.stream().mapToInt(Option::getLength).min().orElse(0);
            result = result.stream().filter(o -> o.length == min).collect(Collectors.toList());
        } else {

            result = neighbouringEnemies.stream()
                    .map(c -> new Option(c.node, 1, c))
                    .collect(Collectors.toList());
        }
        result.sort(Comparator.comparing((Option o) -> o.getNode().getAttribute(Y)).thenComparing(o -> o.getNode().getAttribute(X)));
        return result;


    }


    private List<Creature> getOtherTypeCreatures(Creature creature) {
        return creature.isElf() ? goblins : elves;
    }

    protected void sortCreatures() {
        creatures.sort(Comparator.comparing((Creature c) -> c.getNode().getAttribute(Y)).thenComparing(c -> c.getNode().getAttribute(X)));
    }

    protected void moveCreature(Creature c, AbstractGraph.Node node) {
        AbstractGraph.Node current = c.getNode();
        if (node.isBlocked() || !node.getFromNodes().contains(current)) {
            throw new IllegalArgumentException();
        }
        c.setNode(node);
        node.setBlocked(true);
        current.setBlocked(false);
    }


    private void parseInput(String map) {
        String[] lines = map.split("\\s*\\n\\s*");
        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[y].length(); x++) {
                char c = lines[y].charAt(x);
                if (isANode(c)) {
                    handlePoint(x, y, c);
                }
            }
        }
    }

    private void handlePoint(int x, int y, char c) {
        AbstractGraph.Node node = addNode(y, x);
        if ('E' == c || 'G' == c) {
            Creature creature = createCreature(node, c);
            node.setBlocked(true);
            creatures.add(creature);
            if ('E' == c) {
                elves.add(creature);
            } else {
                goblins.add(creature);
            }
        }
    }

    private Creature createCreature(AbstractGraph.Node node, char c) {
        return new Creature('E' == c ? attackPower + boost : attackPower, initialHitPoint, node, 'E' == c);
    }

    private AbstractGraph.Node addNode(int y, int x) {
        AbstractGraph.Node node = graph.addNode(toNodeName(x, y));
        node.addAttribute(X, x);
        node.addAttribute(Y, y);
        addRelationShips(node);
        return node;
    }

    private void addRelationShips(AbstractGraph.Node node) {
        int x = node.getAttribute(X);
        int y = node.getAttribute(Y);
        AbstractGraph.Node left = graph.getNode(toNodeName(x - 1, y));
        if (left != null) {
            graph.addBidirectedEdge(left, node, 1);
        }
        AbstractGraph.Node above = graph.getNode(toNodeName(x, y - 1));
        if (above != null) {
            graph.addBidirectedEdge(above, node, 1);
        }
    }

    public String toNodeName(int x, int y) {
        return x + "-" + y;
    }


    private boolean isANode(char c) {
        return '.' == c || 'E' == c || 'G' == c;
    }

    @Data
    class Creature {
        private final int attackingPower;
        private int hitPoint;
        private AbstractGraph.Node node;
        private boolean elf;
        private boolean dead = false;

        public Creature(int attackingPower, int hitPoint, AbstractGraph.Node node, boolean elf) {
            this.attackingPower = attackingPower;
            this.hitPoint = hitPoint;
            this.node = node;
            this.elf = elf;
        }

        private void hit(int attackingPower) {
            hitPoint -= attackingPower;
            if (hitPoint <= 0) {
                dead = true;
                node.setBlocked(false);
            }
        }

        @Override
        public String toString() {
            return (elf ? "E" : "G") + "=" + hitPoint;
        }
    }

    public void print() {
        for (int y = 0; y < 25; y++) {
            List<Creature> c = new ArrayList<>();
            for (int x = 0; x < 35; x++) {
                String potentialNodeName = toNodeName(x, y);
                if (graph.getNode(potentialNodeName) == null) {
                    System.out.print("#");
                } else {
                    Creature creature = getCreature(potentialNodeName);
                    if (creature != null && !creature.isDead()) {
                        c.add(creature);
                        System.out.print(creature.elf ? "E" : "G");
                    } else {
                        System.out.print(".");
                    }
                }
            }
            System.out.print("   " + c);
            System.out.println();
        }
        System.out.println();
    }

    public Creature getCreature(String potentialNodeName) {
        for (Creature creature : creatures) {
            String name = creature.getNode().getName();
            if (potentialNodeName.equals(name)) {
                return creature;
            }
        }
        return null;
    }


    @Data
    @AllArgsConstructor
    protected class Option {
        private AbstractGraph.Node node;
        private int length;
        private Creature creature;
    }

    protected class NoMoreEnemiesException extends Exception {

    }
}
