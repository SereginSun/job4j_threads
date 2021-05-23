package ru.job4j.concurrent;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CountTest {

    private class ThreadCount extends Thread {
        private final Count count;

        private ThreadCount(Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }
    }

    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        /**
         * Создаём счётчик.
         */
        final Count count = new Count();

        /**
         * Создаём нити.
         */
        Thread first = new ThreadCount(count);
        Thread second = new ThreadCount(count);

        /**
         * Запускаем нити.
         */
        first.start();
        second.start();

        /**
         * Заставляем главную нить дождаться выполнения наших нитей.
         */
        first.join();
        second.join();

        /**
         * проверяем результат.
         */
        assertThat(count.getValue(), is(2));
    }
}