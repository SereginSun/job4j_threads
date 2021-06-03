package ru.job4j.concurrent.wait;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void whenAddThenConsumed() throws InterruptedException {
        SimpleBlockingQueue<String> queue = new SimpleBlockingQueue<>(3);
        Thread producer = new Thread(
                () -> {
                        queue.offer("first");
                        queue.offer("second");
                        queue.offer("third");
                }
        );
        Thread consumer = new Thread(
                () -> {
                        queue.pool();
                        queue.pool();
                }
        );
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(queue.pool(), is("third"));
    }
}