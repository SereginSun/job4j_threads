package ru.job4j.concurrent;

public class Wget {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    try {
                        for (int index = 0; index <= 100; index++) {
                            System.out.printf("\rLoading: %d%%", index);
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
        );
        thread.start();
        Thread.sleep(10000);
        thread.interrupt();
    }
}
