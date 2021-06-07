package ru.job4j.concurrent.nonblocking;

import java.util.concurrent.atomic.AtomicReference;

public class CASCounter {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        int value;
        int nextValue;
        do {
            value = count.get();
            nextValue = value + 1;
        } while (!count.compareAndSet(value, nextValue));
    }

    public int get() {
        return count.get();
    }

    public static void main(String[] args) throws InterruptedException {
        CASCounter counter = new CASCounter();
        Thread first = new Thread(
                () -> {
                    counter.increment();
                    counter.increment();
                }
        );
        Thread second = new Thread(
                () -> {
                    counter.increment();
                    counter.increment();
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();

        System.out.println(counter.get());
    }
}
