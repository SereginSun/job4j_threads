package ru.job4j.concurrent.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    private final int limit;

    public SimpleBlockingQueue(int limit) {
        this.limit = limit;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            if (queue.size() == limit) {
                this.wait();
            }
            queue.offer(value);
            this.notify();
        }
    }

    public T pool() throws InterruptedException {
        synchronized (this) {
            if (queue.size() == 0) {
                this.wait();
//                Thread.currentThread().interrupt();
            }
            T result = queue.poll();
            this.notify();
            return result;
        }
    }

    public boolean isEmpty() {
        synchronized (this) {
            return queue.isEmpty();
        }
    }
}
