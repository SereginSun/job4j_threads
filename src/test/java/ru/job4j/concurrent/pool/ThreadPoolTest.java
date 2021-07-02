package ru.job4j.concurrent.pool;

import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ThreadPoolTest {

    @Test
    public void work() throws InterruptedException {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        ThreadPool threadPool = new ThreadPool();
        for (int i = 0; i < 5; i++) {
            int num = i;
            Thread thread = new Thread(() -> list.add(num));
            thread.start();
            thread.join();
            threadPool.work(thread);
        }
        threadPool.shutdown();
        assertThat(list.size(), is(5));
    }
}