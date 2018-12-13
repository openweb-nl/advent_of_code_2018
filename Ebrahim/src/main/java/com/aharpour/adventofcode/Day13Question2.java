package com.aharpour.adventofcode;

import java.util.HashSet;
import java.util.Set;

import com.aharpour.adventofcode.utils.IntPair;

public class Day13Question2 extends Day13 {

    public Day13Question2(String input) {
        super(input);
    }

    public IntPair calculate() {
        IntPair result;
        Set<Integer> collided = new HashSet<>(20);
        while (true) {

            for (int i = 0, cartsSize = carts.size(); i < cartsSize; i++) {
                Cart cart = carts.get(i);
                if (!collided.contains(i)) {
                    int collisionIndex = cart.updateCart();
                    if (collisionIndex != -1) {
                        collided.add(i);
                        collided.add(collisionIndex);
                    }
                }
            }

            removeCollided(collided);
            collided = new HashSet<>();
            if (carts.size() == 1) {
                Cart cart = carts.get(0);
                result = new IntPair(cart.getX(), cart.getY());
                break;
            }
            sortCarts();
        }
        return result;
    }

    private void removeCollided(Set<Integer> collided) {
        for (int i = carts.size() - 1; i >= 0; i--) {
            if (collided.contains(i)) {
                carts.remove(i);
            }
        }
    }
}