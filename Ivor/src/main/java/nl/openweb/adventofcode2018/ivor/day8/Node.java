package nl.openweb.adventofcode2018.ivor.day8;

import java.util.ArrayList;
import java.util.List;

/**
 * A header, which is always exactly two numbers:
 * The quantity of child nodes.
 * The quantity of metadata entries.
 * Zero or more child nodes (as specified in the header).
 * One or more metadata entries (as specified in the header).
 *
 * @author Ivor
 */
public class Node {
    private final String id;
    private Node parent;
    /**
     * first and second number are 1) The quantity of child nodes and 2) The quantity of metadata entries.
     */
    private final List<Integer> numbers;

    // lazy calculated
    private List<Node> childNodes = null;
    private int length = -1;

    public Node(String id, Node parent, List<Integer> numbers) {
        this.id = id;
        this.parent = parent;
        this.numbers = numbers;
    }

    public int getChildNodeCount() {
        return numbers.get(0);
    }

    public int getMetadataCount() {
        return numbers.get(1);
    }

    public int getLength() {
        if (length == -1) {
            length = 2 + getMetadataCount() + getChildNodes().stream().mapToInt(Node::getLength).sum();
        }
        return length;
    }

    public int getMetadataSum() {
        int length = getLength();
        int result = numbers.subList(length - getMetadataCount(), length).stream()
                .mapToInt(Integer::intValue)
                .sum();
        for (Node child : getChildNodes()) {
            result += child.getMetadataSum();
        }
        return result;
    }


    public int getValue() {
        List<Node> children = getChildNodes();
        if (children.isEmpty()) {
            return getMetadataSum();
        }
        int result = 0;
        List<Integer> metadata = numbers.subList(getLength() - getMetadataCount(), getLength());
        for (Integer index : metadata) {
            // if index==1 then then take .get(0)
            // check if index exists
            if (index <= children.size()) {
                result += children.get(index - 1).getValue();
            }
        }
        return result;
    }

    private List<Node> getChildNodes() {
        if (childNodes == null) {
            List<Node> nodes = new ArrayList<>();
            int startIndex = 2;
            for (int index = 0; index < getChildNodeCount(); index++) {
                int endIndex = numbers.size() - getMetadataCount();
                List<Integer> subList = numbers.subList(startIndex, endIndex);
                Node childNode = new Node(id + "_" + index, this, subList);
                nodes.add(childNode);
                startIndex += childNode.getLength();
            }
//            System.out.println("Node " + this + " has children " + nodes);
            childNodes = nodes;
        }
        return childNodes;
    }

    @Override
    public String toString() {
        return "[Node " + id + ", " + numbers + "]";
    }
}
