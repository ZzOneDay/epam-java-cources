package com.epam.university.java.core.task026;

public class Task026Impl implements Task026 {
    private static int LIMIT_LOW_MIN = 'a';
    private static int LIMIT_LOW_MAX = 'z';

    private static int LIMIT_HIGH_MIN = 'A';
    private static int LIMIT_HIGH_MAX = 'Z';


    @Override
    public String encrypt(String sourceString, int shift) {
        if (sourceString == null) {
            throw new IllegalArgumentException();
        }

        shift = getCorrectShift(shift);
        char[] chars = sourceString.toCharArray();
        StringBuilder result = new StringBuilder();
        for (char ch : chars) {
            if (!Character.isLetter(ch)) {
                result.append(ch);
                continue;
            }
            String stringChar = ch + "";

            if (stringChar.equals(stringChar.toUpperCase())) {
                //char is high
                int valueChar = ch + shift;
                char charOfValue = (char) valueChar;
                char myChar = getCorrectChar(charOfValue, LIMIT_HIGH_MIN, LIMIT_HIGH_MAX);
                result.append(myChar);
            } else {
                //char is low
                int valueChar = ch + shift;
                char charOfValue = (char) valueChar;
                char myChar = getCorrectChar(charOfValue, LIMIT_LOW_MIN, LIMIT_LOW_MAX);
                result.append(myChar);
            }
        }
        return result.toString();
    }

    @Override
    public String decrypt(String encryptedString, int shift) {
        return encrypt(encryptedString, -shift);
    }

    private char getCorrectChar(char anyCharacter, int limitMin, int limitMax) {
        int charValue = anyCharacter;
        if (charValue < limitMin) {
            charValue = (limitMax - (limitMin - charValue)) + 1;
        }
        if (charValue > limitMax) {
            charValue = (limitMin + (charValue - limitMax)) - 1;
        }
        return (char) charValue;
    }

    private int getCorrectShift(int shift) {
        if (shift > 0) {
            while (shift > 26) {
                shift = shift - 26;
            }
        } else {
            while (shift < -26) {
                shift = shift + 26;
            }
        }
        return shift;
    }
}
