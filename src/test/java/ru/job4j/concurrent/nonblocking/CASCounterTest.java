package ru.job4j.concurrent.nonblocking;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CASCounterTest {

    @Test
    public void when2ThreadIncrementCount() throws InterruptedException {
        CASCounter count = new CASCounter();
        Thread first = new Thread(
                () -> {
                    for (int i = 0; i < 50; i++) {
                        count.increment();
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    for (int i = 0; i < 50; i++) {
                        count.increment();
                    }
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(count.get(), is(100));
    }
}