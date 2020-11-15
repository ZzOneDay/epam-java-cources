package com.epam.university.java.core.task056;

public class Medicine {
    private int firstDay;
    private int lastDay;
    private int countTablets;
    private int index;


    /**
     * Create medicine from File.
     *
     * @param index order in file.
     * @param firstDay start of reception.
     * @param lastDay finish of reception.
     * @param countTablets count tablets in course.
     */
    public Medicine(int index, int firstDay, int lastDay, int countTablets) {
        this.index = index;
        this.firstDay = firstDay;
        this.lastDay = lastDay;
        this.countTablets = countTablets;
    }

    public int getFirstDay() {
        return firstDay;
    }

    public int getLastDay() {
        return lastDay;
    }

    public int getCountTablets() {
        return countTablets;
    }

    public int getIndex() {
        return index;
    }
}
