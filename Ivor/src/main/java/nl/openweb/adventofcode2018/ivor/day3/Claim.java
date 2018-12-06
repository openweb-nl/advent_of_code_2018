package nl.openweb.adventofcode2018.ivor.day3;

import java.awt.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Ivor
 */
public class Claim {
    private int id;
    private int left;
    private int top;
    private int width;
    private int height;
    private Set<Point> area = null; // lazy instantiation


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Set<Point> getArea() {
        if (area == null) {
            area = new HashSet<>();
            for (int x = left + 1; x <= (left + width); x++) {
                for (int y = top + 1; y <= (top + height); y++) {
                    area.add(new Point(x, y));
                }
            }
        }
        return new HashSet<>(area);
    }

    public boolean isOverlapping(Claim other) {
        boolean horizontalOverlap = (
                other.left + other.width > left &&
                        other.left < (left + width)
                );
        boolean verticalOverlap = (
                other.top + other.height > top &&
                        other.top < (top + height)
                );
        return  horizontalOverlap && verticalOverlap;
//        My first (lazy) solution was to use Retain all on area. But that is time consuming... (time to get q2 dropped from 68s to 152ms)
//        Set<Point> area = getArea();
//        area.retainAll(other.getArea());
//        return !area.isEmpty();
    }

    @Override
    public String toString() {
        return "Claim{" +
                "id=" + id +
                ", left=" + left +
                ", top=" + top +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Claim claim = (Claim) o;
        return id == claim.id &&
                left == claim.left &&
                top == claim.top &&
                width == claim.width &&
                height == claim.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, left, top, width, height);
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }
}
