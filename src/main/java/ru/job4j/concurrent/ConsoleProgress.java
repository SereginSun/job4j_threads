package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        char[] process = {'\\', '|', '/'};
        while (!Thread.currentThread().isInterrupted()) {
            try {
                for (char proc : process) {
                    Thread.sleep(500);
                    System.out.print("\rLoading ... " + proc);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(10000);
        progress.interrupt();
    }
}
