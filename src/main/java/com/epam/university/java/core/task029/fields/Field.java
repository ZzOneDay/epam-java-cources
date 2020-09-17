package com.epam.university.java.core.task029.fields;

import com.epam.university.java.core.task029.words.Word;

import java.util.ArrayList;

public interface Field {
    ArrayList<Cell> getCells();

    ArrayList<Cell> getConnects();

    void setIsFull(boolean value);

    boolean isFull();

    Word getWord();
}
