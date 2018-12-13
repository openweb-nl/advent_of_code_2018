package com.aharpour.adventofcode;

import java.util.*;

import com.aharpour.adventofcode.utils.geometry.TwoDPoint;


import static com.aharpour.adventofcode.Day13.Symbol.*;

/**
 * @author Ebrahim Aharpour
 * @since 12/13/2018
 */
public class Day13 {

    private Map<TwoDPoint, Symbol> tracks = new HashMap<>(10000);

    protected List<Cart> carts = new ArrayList<>();

    public Day13(String string) {
        String[] split = string.split("\\n");
        for (int y = 0; y < split.length; y++) {
            char[] chars = split[y].toCharArray();
            for (int x = 0, charsLength = chars.length; x < charsLength; x++) {
                char c = chars[x];
                addToTracksIfNeeded(y, x, c);
                addToCartsIfNeeded(y, x, c);
            }
        }
        sortCarts();
    }


    protected void sortCarts() {
        carts.sort(Comparator.comparing(TwoDPoint::getY).thenComparing(TwoDPoint::getX));
    }

    private void addToCartsIfNeeded(int y, int x, char c) {
        if (c == '<') {
            carts.add(new Cart(x, y, new TwoDPoint(-1, 0)));
        }
        if (c == '>') {
            carts.add(new Cart(x, y, new TwoDPoint(1, 0)));
        }
        if (c == '^') {
            carts.add(new Cart(x, y, new TwoDPoint(0, -1)));
        }
        if (c == 'v') {
            carts.add(new Cart(x, y, new TwoDPoint(0, 1)));
        }
    }

    private void addToTracksIfNeeded(int y, int x, char c) {
        if (c == '+') {
            tracks.put(new TwoDPoint(x, y), Symbol.PLUS);
        }
        if (c == '/') {
            tracks.put(new TwoDPoint(x, y), SLASH);
        }
        if (c == '\\') {
            tracks.put(new TwoDPoint(x, y), Symbol.BACKSLASH);
        }
    }

    protected enum Symbol {
        SLASH, BACKSLASH, PLUS
    }

    protected class Cart extends TwoDPoint {
        private int intersection = 0;

        public Cart(int x, int y, TwoDPoint velocity) {
            super(x, y, velocity);
        }


        public int updateCart() {

            this.secondsForward(1);
            Symbol symbol = tracks.get(this);
            if (symbol != null) {
                if (symbol == SLASH) {
                    dealWithSlash();
                }
                if (symbol == BACKSLASH) {
                    dealWithBackslash();
                }
                if (symbol == PLUS) {
                    dealWithPlus();
                }
            }

            return getCollisionIndex();
        }



        private void dealWithPlus() {
            if (intersection == 0) {
                turnLeft();
            } else if (intersection == 2) {
                turnRight();
            }
            intersection = (intersection + 1) % 3;
        }

        private void turnLeft() {
            TwoDPoint velocity = getVelocity();
            int vx = velocity.getX();
            int vy = velocity.getY();
            velocity.setX(vy);
            velocity.setY(vx * -1);
        }

        private void turnRight() {
            TwoDPoint velocity = getVelocity();
            int vx = velocity.getX();
            int vy = velocity.getY();
            velocity.setX(vy * -1);
            velocity.setY(vx);
        }

        private void dealWithBackslash() {
            TwoDPoint velocity = getVelocity();
            int vx = velocity.getX();
            int vy = velocity.getY();
            velocity.setX(vy);
            velocity.setY(vx);
        }

        private void dealWithSlash() {
            TwoDPoint velocity = getVelocity();
            int vx = velocity.getX();
            int vy = velocity.getY();
            velocity.setX(vy * -1);
            velocity.setY(vx * -1);
        }

        public boolean coordinateEquals(Cart c) {
            if (this == c) return true;
            if (c == null || getClass() != c.getClass()) return false;
            if (!super.equals(c)) return false;
            return this.getX() == c.getX() &&
                    this.getY() == c.getY();
        }

        public int getCollisionIndex() {
            int collisionIndex = -1;
            for (int i = 0; i < carts.size(); i++) {
                Cart cart = carts.get(i);
                if (this != cart && this.coordinateEquals(cart)) {
                    collisionIndex = i;
                    break;
                }
            }
            return collisionIndex;
        }
    }

}
