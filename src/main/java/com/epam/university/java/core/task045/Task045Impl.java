package com.epam.university.java.core.task045;

import java.util.HashMap;
import java.util.Map;

public class Task045Impl implements Task045 {
    @Override
    public String doAnagram(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }

        if (simpleTest(input)) {
            return input;
        }

        String[] words = input.split("\\s");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            Map<Integer, Character> characterMap = getIntegerCharacterMap(word);
            String otherString = reverseString(word.replaceAll("[^A-Za-z]*", ""));
            result.append(addIntegerToString(otherString, characterMap)).append(" ");
        }
        return result.toString().trim();
    }

    private Map<Integer, Character> getIntegerCharacterMap(String word) {
        int index = 0;
        Map<Integer, Character> map = new HashMap<>();
        for (Character character : word.toCharArray()) {
            if (!Character.isLetter(character)) {
                map.put(index, character);
            }
            index++;
        }
        return map;
    }


    private String addIntegerToString(String string, Map<Integer, Character> mapIntegers) {
        for (Integer index : mapIntegers.keySet()) {
            String firstPart = string.substring(0, index);
            String secondPart = string.substring(index);
            string = firstPart + mapIntegers.get(index) + secondPart;
        }
        return string;
    }

    private String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    private boolean simpleTest(String string) {
        String revers = reverseString(string);
        return revers.equals(string);
    }

}
