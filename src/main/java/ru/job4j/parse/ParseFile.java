package ru.job4j.parse;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized String getContent(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int data;
            while ((data = br.read()) > 0) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return output.toString();
    }

    public String getContentWithoutUnicode() {
        return getContent(symbol -> symbol < 0x80);
    }
}
