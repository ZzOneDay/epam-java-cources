package com.epam.university.java.core.task029.fields;

import com.epam.university.java.core.task029.words.Word;

import java.util.ArrayList;

public class VerticalField implements Field {
    private ArrayList<Cell> cells;
    private boolean isFull = false;

    public VerticalField(ArrayList<Cell> cells) {
        this.cells = cells;
    }

    @Override
    public ArrayList<Cell> getCells() {
        return cells;
    }

    @Override
    public ArrayList<Cell> getConnects() {
        ArrayList<Cell> list = new ArrayList<>();
        for (Cell cell : cells) {
            if (cell.isHasConnect()) {
                list.add(cell);
            }
        }
        return list;
    }

    @Override
    public void setIsFull(boolean value) {
        isFull = value;
    }

    @Override
    public boolean isFull() {
        return isFull;
    }

    @Override
    public Word getWord() {
        StringBuilder word = new StringBuilder();
        for (Cell cell : cells) {
            word.append(cell.getLetta());
        }
        return Word.getWord(word.toString());
    }
}
