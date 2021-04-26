package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream out = new FileOutputStream("pom_tmp.xml")){
            byte[] dataBuffer = new byte[speed];
            int bytesRead;
            long wait;
            while ((bytesRead = in.read(dataBuffer, 0, speed)) != -1) {
                long start = System.currentTimeMillis();
                out.write(dataBuffer, 0, bytesRead);
                wait = System.currentTimeMillis() - start;
                if (wait < 1000) {
                    Thread.sleep(wait);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
