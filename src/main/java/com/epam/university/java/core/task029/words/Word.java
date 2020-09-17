package com.epam.university.java.core.task029.words;

import java.util.ArrayList;

public class Word {
    private ArrayList<Character> letters;
    private boolean isUsed = false;

    private Word(ArrayList<Character> letters) {
        this.letters = letters;
    }

    public ArrayList<Character> getLetters() {
        return letters;
    }

    public void setIsUsed(boolean value) {
        isUsed = value;
    }

    public boolean isUsed() {
        return isUsed;
    }

    /**
     * Create object class Word.
     * <p>
     * This application don't work this String,
     * This work this Words, Words is array of characters.
     * And, we can understand used this word or not.
     * </p>
     *
     * @param string string that have word for crossword.
     * @return object Word that have arrayList of characters and boolean isUsed.
     */
    public static Word getWord(String string) {
        ArrayList<Character> letters = new ArrayList<>();
        for (int i = 0; i < string.length(); i++) {
            letters.add(string.charAt(i));
        }
        return new Word(letters);
    }

    @Override
    public boolean equals(Object obj) {
        Word otherWord = (Word) obj;
        return this.letters.equals(otherWord.letters);
    }
}
