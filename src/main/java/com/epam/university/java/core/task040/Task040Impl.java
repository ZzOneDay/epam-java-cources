package com.epam.university.java.core.task040;

public class Task040Impl implements Task040 {
    @Override
    public int countScore(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException();
        }

        FrameTable frameTable = new FrameTable();
        String[] strings = str.split(" ");

        int id = 0;
        for (String message : strings) {
            frameTable.addToFrames(Frame.getInstance(id, message));
            id++;
        }

        return frameTable.getScore();
    }
}
