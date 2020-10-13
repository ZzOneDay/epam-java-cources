package com.epam.university.java.core.task040;

import java.util.ArrayList;

public interface FrameInterface {
    int getScore(ArrayList<FrameInterface> frames);

    int getFirstStrike();

    int getSecondStrike();

    FrameType getFrameType();
}
