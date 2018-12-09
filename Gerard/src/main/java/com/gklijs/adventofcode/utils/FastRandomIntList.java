package com.gklijs.adventofcode.utils;

import java.util.stream.IntStream;

import com.gklijs.adventofcode.errors.InvalidIndexException;
import com.gklijs.adventofcode.errors.NoMoreEmptyBucketsException;

public class FastRandomIntList {

    private final int[][] items;
    private final int[] sizes;
    private final int arraySize;
    private final int buckets;
    private final int maxCapacity;
    private int size = 0;

    /*
    The capacity given will be used to try to optimise the list
     */
    public FastRandomIntList(int capacity) {
        arraySize = (int) Math.sqrt(capacity) + 1;
        buckets = 2 * arraySize;
        items = new int[buckets][arraySize];
        sizes = new int[buckets];
        maxCapacity = arraySize * arraySize;
    }

    public int get(final int index) {
        int[] pointer = getPointerForIndex(index);
        return items[pointer[0]][pointer[1]];
    }

    public void add(int item) {
        add(item, size);
    }

    public void add(int index, int item) {
        if (index < 0 || index >= maxCapacity) {
            throw new InvalidIndexException(index);
        }
        if (size == 0) {
            items[0][0] = item;
            sizes[0]++;
            size++;
        } else {
            addInternal(item, index);
        }
    }

    private void addInternal(final int item, final int index) {
        int[] pointer = getPointerForIndex(index);
        if (sizes[pointer[0]] == arraySize) {
            freeUp(pointer[0]);
            pointer = getPointerForIndex(index);
        }
        if (pointer[1] < sizes[pointer[0]]) {
            System.arraycopy(items[pointer[0]], pointer[1], items[pointer[0]], pointer[1] + 1, sizes[pointer[0]] - pointer[1]);
        }
        items[pointer[0]][pointer[1]] = item;
        sizes[pointer[0]]++;
        size++;
    }

    private int firstEmptyBucket(int from) {
        for (int i = from; i < buckets; i++) {
            if (sizes[i] == 0) {
                return i;
            }
        }
        throw new NoMoreEmptyBucketsException();
    }

    private void freeUp(int bucket) {
        int firstEmptyBucket = firstEmptyBucket(bucket);
        int[] emptyBucket = items[firstEmptyBucket];
        for (int i = firstEmptyBucket; i > bucket; i--) {
            items[i] = items[i - 1];
            sizes[i] = sizes[i - 1];
        }
        int half = arraySize / 2;
        items[bucket] = emptyBucket;
        System.arraycopy(items[bucket + 1], 0, items[bucket], 0, half);
        System.arraycopy(items[bucket + 1], half, items[bucket + 1], 0, arraySize - half);
        sizes[bucket] = half;
        sizes[bucket + 1] = arraySize - half;
    }

    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new InvalidIndexException(index);
        }
        int[] pointer = getPointerForIndex(index);
        int current = items[pointer[0]][pointer[1]];
        if (sizes[pointer[0]] > pointer[1]) {
            System.arraycopy(items[pointer[0]], pointer[1] + 1, items[pointer[0]], pointer[1], sizes[pointer[0]] - (pointer[1] + 1));
        }
        sizes[pointer[0]]--;
        size--;
        return current;
    }

    private int[] getPointerForIndex(int index) {
        int indexInArray = index;
        for (int i = 0; i < buckets; i++) {
            if (indexInArray - sizes[i] < 0 || sizes[i] == 0) {
                return new int[]{i, indexInArray};
            } else {
                indexInArray -= sizes[i];
            }
        }
        throw new InvalidIndexException(index);
    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        IntStream.range(0, size - 1).forEach(x -> builder.append(get(x)).append(", "));
        builder.append(get(size));
        builder.append(']');
        return builder.toString();
    }
}
