package com.epam.university.java.core.task040;

import java.util.ArrayList;

class FrameTable {
    private ArrayList<FrameInterface> frames = new ArrayList<>();

    void addToFrames(FrameInterface frame) {
        frames.add(frame);
    }

    int getScore() {
        int value = 0;
        for (FrameInterface frameInterface : frames) {
            value += frameInterface.getScore(frames);
        }
        return value;
    }
}
