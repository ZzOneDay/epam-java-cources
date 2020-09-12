package com.epam.university.java.core.task025;

import java.util.ArrayList;

public class Task025Impl implements Task025 {
    @Override
    public int getAmountOfAlteredLetters(String sourceMessage) {
        if (sourceMessage == null) {
            throw new IllegalArgumentException();
        }

        ArrayList<String> commands = new ArrayList<>();
        int index = 1;
        while (true) {
            if (sourceMessage.length() < 3) {
                commands.add(sourceMessage);
                break;
            }

            index++;

            if (index % 3 == 0) {
                commands.add(sourceMessage.substring(0, 3));
                sourceMessage = sourceMessage.substring(3);
            }
        }

        int incorrectCount = 0;
        for (String string : commands) {
            incorrectCount += getIncorrectCharacters(string);
        }
        return incorrectCount;
    }

    private int getIncorrectCharacters(String string) {
        int incorrectCount = 0;
        String correctString = "SOS";
        for (int i = 0; i < string.length(); i++) {
            if (!(string.charAt(i) == correctString.charAt(i))) {
                incorrectCount++;
            }
        }
        return incorrectCount;
    }
}
