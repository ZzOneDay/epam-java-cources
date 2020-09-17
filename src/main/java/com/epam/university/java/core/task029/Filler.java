package com.epam.university.java.core.task029;

import com.epam.university.java.core.task029.fields.Cell;
import com.epam.university.java.core.task029.fields.Field;
import com.epam.university.java.core.task029.words.Word;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Filler.
 * <p>
 * This is core of application. It is work. How?
 * 1. Find field and word these have one value of size. And complete.
 * 2. When, application have more one solution about word and field,
 * application full field and empty field, and check for connect ceil.
 * if these fields have connect, we try to complete field, and check result,
 * if result is ok, go next word and fields.
 * </p>
 *
 * @author Pavel Novikov
 * @since 1.0
 */
class Filler {
    private CrosswordPlace crosswordPlace;
    private ArrayList<Word> words;

    Filler(CrosswordPlace crosswordPlace, ArrayList<Word> words) {
        this.crosswordPlace = crosswordPlace;
        this.words = words;
    }

    CrosswordPlace getFilledCrossword() {
        ArrayList<Field> fields = getAllFields();
        fillFields(fields, words);
        return crosswordPlace;
    }

    private void fillFields(ArrayList<Field> fields, ArrayList<Word> words) {
        HashMap<Integer, ArrayList<Word>> mapWords = getMapWords(words);
        HashMap<Integer, ArrayList<Field>> mapFields = getMapFields(fields);
        for (Integer sizeWord : mapWords.keySet()) {
            int sizeList = mapWords.get(sizeWord).size();
            if (sizeList == 1) {
                Field field = mapFields.get(sizeWord).get(0);
                Word word = mapWords.get(sizeWord).get(0);
                fillFieldByWord(field, word);
            }
        }

        while (hasNotUsedWords(words)) {
            for (Field field1 : fields) {
                for (Field field2 : fields) {
                    if (field1.isFull() && !field2.isFull()) {
                        Cell connect = fieldsOneHasConnect(field1, field2);
                        if (connect != null) {
                            tryToComplete(connect, field2, words);
                        }
                    }
                }
            }
        }
    }

    private boolean hasNotUsedWords(ArrayList<Word> words) {
        for (Word word : words) {
            if (!word.isUsed()) {
                return true;
            }
        }
        return false;
    }

    private void tryToComplete(Cell connect, Field emptyField, ArrayList<Word> words) {
        {
            for (Word word : words) {
                if (!word.isUsed() && word.getLetters().contains(connect.getLetta())) {
                    fillFieldByWord(emptyField, word);
                    if (isCorrectFullField(emptyField, words)) {
                        break;
                    } else {
                        clearCellsOfFields(emptyField);
                        resetField(emptyField);
                        resetWord(word);
                    }
                }
            }
        }
    }

    private boolean isCorrectFullField(Field field, ArrayList<Word> words) {
        Word wordByField = field.getWord();
        return words.contains(wordByField);
    }

    private Cell fieldsOneHasConnect(Field field1, Field field2) {
        for (Cell cell1 : field1.getConnects()) {
            for (Cell cell2 : field2.getConnects()) {
                if (cell1.equals(cell2)) {
                    return cell1;
                }
            }
        }
        return null;
    }

    private HashMap<Integer, ArrayList<Field>> getMapFields(ArrayList<Field> fields) {
        HashMap<Integer, ArrayList<Field>> map = new HashMap<>();
        for (Field field : fields) {
            int size = field.getCells().size();
            if (map.containsKey(size)) {
                map.get(size).add(field);
            } else {
                ArrayList<Field> fieldsList = new ArrayList<>();
                fieldsList.add(field);
                map.put(size, fieldsList);
            }
        }
        return map;
    }

    private HashMap<Integer, ArrayList<Word>> getMapWords(ArrayList<Word> words) {
        HashMap<Integer, ArrayList<Word>> map = new HashMap<>();
        for (Word word : words) {
            int size = word.getLetters().size();
            if (map.containsKey(size)) {
                map.get(size).add(word);
            } else {
                ArrayList<Word> wordsList = new ArrayList<>();
                wordsList.add(word);
                map.put(size, wordsList);
            }
        }
        return map;
    }

    private void fillFieldByWord(Field field, Word word) {
        if (field.getCells().size() != word.getLetters().size()) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < field.getCells().size(); i++) {
            Cell cell = field.getCells().get(i);
            if (cell.getLetta() == null) {
                cell.setLetta(word.getLetters().get(i));
            }
        }
        field.setIsFull(true);
        word.setIsUsed(true);
    }

    private void clearCellsOfFields(Field field) {
        for (Cell cell : field.getCells()) {
            if (cell.isHasConnect()) {
                continue;
            }
            cell.setLetta(null);
        }
    }

    private void resetField(Field field) {
        field.setIsFull(false);

    }

    private void resetWord(Word word) {
        word.setIsUsed(false);

    }

    private ArrayList<Field> getAllFields() {
        ArrayList<Field> fields = new ArrayList<>();
        fields.addAll(crosswordPlace.getSimpleVerticalFields());
        fields.addAll(crosswordPlace.getSimpleHorizontalFields());
        return fields;
    }
}
