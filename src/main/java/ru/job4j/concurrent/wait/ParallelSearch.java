package ru.job4j.concurrent.wait;

public class ParallelSearch {

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
        final Thread consumer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println(queue.pool());
                    }
                }
        );
        consumer.start();

        final Thread producer = new Thread(
                () -> {
                    for (int i = 0; i != 3; i++) {
                        queue.offer(i);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    consumer.interrupt();
                }
        );
        producer.start();
    }
}
