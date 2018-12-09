package com.gklijs.adventofcode.errors;

public class InvalidIndexException extends RuntimeException {

    public InvalidIndexException(int index) {
        super(index + " is not a valid index value");
    }
}
