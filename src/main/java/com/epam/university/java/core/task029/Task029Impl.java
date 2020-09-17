package com.epam.university.java.core.task029;

import com.epam.university.java.core.task029.words.Word;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Crossword.
 * <p>
 * How it work? - It's easy. We create object class {@link CrosswordPlace}.
 * This object have horizontal and vertical
 * object {@link com.epam.university.java.core.task029.fields.Field}.
 * When you have good crossword, you can print it and see,
 * correct fields or not. We'll see empty fields.
 * After that, You create {@link Filler} , that complete your crossword.
 * How Filler work? You can read info in class {@link Filler}.
 * </p>
 *
 * @author Pavel Novikov
 * @since 1.0
 */

public class Task029Impl implements Task029 {
    @Override
    public Collection<String> fillCrossword(Collection<String> rows, Collection<String> words) {
        if (rows == null || words == null) {
            throw new IllegalArgumentException();
        }
        CrosswordPlace crosswordPlace = CrosswordPlace.getCrossword(rows);
        ArrayList<Word> wordsList = new ArrayList<>();
        for (String string : words) {
            wordsList.add(Word.getWord(string));
        }
        Filler filler = new Filler(crosswordPlace, wordsList);
        return filler.getFilledCrossword().getResult();
    }
}
