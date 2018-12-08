package com.aharpour.adventofcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.aharpour.adventofcode.utils.graph.DirectionalGraph;

/**
 * @author Ebrahim Aharpour
 * @since 12/7/2018
 */
public class Day7 {

    private static final Pattern pattern = Pattern.compile("Step ([A-Za-z]) must be finished before step ([A-Za-z]) can begin.");
    private DirectionalGraph graph = new DirectionalGraph();
    protected List<DirectionalGraph.Node> nodes;
    protected List<DirectionalGraph.Node> dones = new ArrayList<>();

    protected void parseInput(String string) {
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            graph.addRelationShip(matcher.group(1), matcher.group(2));
        }

        nodes = graph.getNodes().stream()
                .sorted(Comparator.comparing(DirectionalGraph.Node::getName))
                .collect(Collectors.toList());
    }
}
