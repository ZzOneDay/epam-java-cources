package com.epam.university.java.core.task024;

import java.util.ArrayList;
import java.util.Collection;

public class Task024Impl implements Task024 {
    @Override
    public Collection<String> getWordsCount(String source) {
        if (source == null) {
            throw new IllegalArgumentException();
        }

        ArrayList<String> words = new ArrayList<>();
        String string = "";
        int index = 0;

        for (Character character : source.toCharArray()) {
            index++;
            if (character.toString().equals(character.toString().toUpperCase())
                    && string.length() != 0) {
                words.add(string.toLowerCase());
                string = character.toString();
                continue;
            }
            string += character;
            if (index == source.length()) {
                words.add(string.toLowerCase());
            }
        }
        return words;
    }
}
