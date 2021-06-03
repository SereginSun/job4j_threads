package ru.job4j.concurrent.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    private final int limit;

    public SimpleBlockingQueue(int limit) {
        this.limit = limit;
    }

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            this.notify();
            if (queue.size() == limit) {
                    this.wait();
            }
            queue.offer(value);
        }
    }

    public T pool() throws InterruptedException {
        synchronized (this) {
            this.notify();
            if (queue.size() == 0) {
                    this.wait();
                    Thread.currentThread().interrupt();
            }
            return queue.poll();
        }
    }

    public boolean isEmpty() {
        synchronized (this) {
            return queue.isEmpty();
        }
    }
}
