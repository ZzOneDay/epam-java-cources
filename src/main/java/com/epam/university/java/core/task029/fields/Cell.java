package com.epam.university.java.core.task029.fields;

import java.util.Objects;

public class Cell implements Comparable {
    private int x;
    private int y;
    private boolean hasConnect = false;
    private Character letta = null;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setHasConnect() {
        this.hasConnect = true;
    }

    public boolean isHasConnect() {
        return hasConnect;
    }

    public void setLetta(Character letta) {
        this.letta = letta;
    }

    public Character getLetta() {
        return letta;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y && letta == cell.letta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int compareTo(Object o) {
        Cell cell1 = this;
        Cell cell2 = (Cell) o;
        if (cell1.getY() == cell2.getY()) {
            if (cell1.getX() > cell2.getX()) {
                return -1;
            } else {
                return 1;
            }
        }

        if (cell1.getX() == cell2.getX()) {
            if (cell1.getY() < cell2.getY()) {
                return 1;
            } else {
                return -1;
            }
        }
        return 0;
    }
}
