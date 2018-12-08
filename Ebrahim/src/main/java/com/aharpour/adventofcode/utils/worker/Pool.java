package com.aharpour.adventofcode.utils.worker;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author Ebrahim Aharpour
 * @since 12/7/2018
 */
public class Pool {

    private final int size;
    private final List<Task<?>> tasks;
    private final Queue<Task<?>> queue = new LinkedList<>();

    public Pool(int size) {
        this.size = size;
        this.tasks = new ArrayList<>(size);
    }

    public void addTask(Task<?> task) {
        queue.add(task);
        checkForWork();
    }

    public void cycle() {
        for (Task<?> task : new ArrayList<>(tasks)) {
            task.cycle();
        }
        checkForWork();
    }

    private void checkForWork() {
        if (tasks.size() < size) {
            Task peek = queue.poll();
            if (peek != null) {
                tasks.add(peek);
            }
        }
    }



    public class Task<T> {
        private int length;
        private final T item;
        private final Consumer<T> consumer;

        public Task(int length, T item, Consumer<T> consumer) {
            this.length = length;
            this.item = item;
            this.consumer = consumer;
        }

        private void cycle() {
            length--;
            if (length == 0) {
                consumer.accept(item);
                tasks.remove(this);
            }
        }
    }
}
