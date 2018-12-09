package com.gklijs.adventofcode.errors;

public class NoMoreEmptyBucketsException extends RuntimeException {

    public NoMoreEmptyBucketsException() {
        super("no more empty buckets, implementation is not perfect..");
    }
}
