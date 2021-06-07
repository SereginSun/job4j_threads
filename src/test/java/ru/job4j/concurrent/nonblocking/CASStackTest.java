package ru.job4j.concurrent.nonblocking;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CASStackTest {

    @Test
    public void when3PushThen3Poll() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        CASStack<Integer> stack = new CASStack<>();
        Thread first = new Thread(
                () -> {
                    stack.push(1);
                    stack.push(2);
                    stack.push(3);
                }
        );
        Thread second = new Thread(
                () -> {
                    buffer.add(stack.poll());
                    buffer.add(stack.poll());
                    buffer.add(stack.poll());
                }
        );
        first.start();
        Thread.sleep(100);
        second.start();
        first.join();
        second.join();
        assertThat(buffer, is(Arrays.asList(3, 2, 1)));
    }
}