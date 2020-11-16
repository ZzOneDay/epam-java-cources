package com.epam.university.java.core.task059;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

public class DefaultFileVisitorImpl extends DefaultFileVisitor {
    private List<String> result = new LinkedList<>();
    private String attribute;

    DefaultFileVisitorImpl(String attribute) {
        this.attribute = attribute;
    }

    @Override
    public List<String> getResult() {
        return result;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String content = new String(Files.readAllBytes(file));
        if (content.contains(attribute)) {
            String path = file.toFile().getPath();
            result.add(path);
        }
        return super.visitFile(file, attrs);
    }
}
