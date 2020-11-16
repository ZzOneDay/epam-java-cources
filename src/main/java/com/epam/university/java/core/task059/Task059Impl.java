package com.epam.university.java.core.task059;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Task059Impl implements Task059 {
    @Override
    public List<String> find(String path, String substring) {
        if (path == null || substring == null) {
            throw new IllegalArgumentException();
        }

        Path testFilePath = Paths.get(path);
        DefaultFileVisitor visitor = new DefaultFileVisitorImpl(substring);

        try {
            Files.walkFileTree(testFilePath, visitor);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error Files.walkFileTree path:"
                    + path + "substring: " + substring);
        }

        return visitor.getResult();
    }
}
