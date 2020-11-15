package com.epam.university.java.core.task057;

public class WindowImpl implements Window {
    private int levelNumber;
    private int numberOfWindow;
    private SideType side;

    /**
     * Window.
     *
     * @param levelNumber    level of House that has window
     * @param numberOfWindow number window of right side.
     * @param side           back side or front side that has window.
     */
    public WindowImpl(int levelNumber, int numberOfWindow, SideType side) {
        this.levelNumber = levelNumber;
        this.numberOfWindow = numberOfWindow;
        this.side = side;
    }

    @Override
    public int getLevelNumber() {
        return levelNumber;
    }

    @Override
    public int getNumberOfWindow() {
        return numberOfWindow;
    }

    @Override
    public SideType getSide() {
        return side;
    }
}
