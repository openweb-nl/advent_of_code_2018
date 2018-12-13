package com.aharpour.adventofcode;

import com.aharpour.adventofcode.utils.IntPair;

public class Day13Question1 extends Day13 {

    public Day13Question1(String input) {
        super(input);
    }


    public IntPair calculate() {
        IntPair result;
        outer:
        while (true) {
            for (int i = 0, cartsSize = carts.size(); i < cartsSize; i++) {
                Cart cart = carts.get(i);
                int collisionIndex = cart.updateCart();
                if (collisionIndex != -1) {
                    result = new IntPair(cart.getX(), cart.getY());
                    break outer;
                }
            }
            sortCarts();
        }
        return result;
    }


}