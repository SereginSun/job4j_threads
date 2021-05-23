package ru.job4j.parse;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
    private final File file;

    public WriteFile(File file) {
        this.file = file;
    }

    public synchronized void saveContent(String content) {
        try (BufferedWriter output = new BufferedWriter(new FileWriter(file))) {
            output.write(content);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
