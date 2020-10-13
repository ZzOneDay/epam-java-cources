package com.epam.university.java.core.task040;

import java.util.ArrayList;

public class FinalityFrame implements FrameInterface {
    private int firstStrike;
    private int secondStrike;
    private String message;

    private FinalityFrame(String message) {
        this.message = message;
    }

    static FinalityFrame getFinalityFrame(String message) {
        FinalityFrame frame = new FinalityFrame(message);
        String[] values = message.split("");
        int[] valuesStrike = new int[3];
        int i = 0;
        for (String value : values) {
            if (value.equals("X")) {
                valuesStrike[i] = 10;
            } else {
                valuesStrike[i] = Integer.parseInt(value);
            }
            i++;
        }
        frame.setFirstStrike(valuesStrike[0]);
        frame.setSecondStrike(valuesStrike[1]);
        frame.setMessage(message);
        return frame;
    }

    @Override
    public int getScore(ArrayList<FrameInterface> frames) {
        int value0 = getScoreOfStrike(message.charAt(0));
        int value1 = getScoreOfStrike(message.charAt(1));
        int value2 = getScoreOfStrike(message.charAt(2));
        return value0 + value1 + value2;
    }

    public int getFirstStrike() {
        return firstStrike;
    }

    public int getSecondStrike() {
        return secondStrike;
    }

    @Override
    public FrameType getFrameType() {
        return FrameType.FINALITY;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    private int getScoreOfStrike(char strike) {
        if (strike == 'X') {
            return 10;
        } else {
            return Integer.parseInt(strike + "");
        }
    }

    private void setFirstStrike(int firstStrike) {
        this.firstStrike = firstStrike;
    }

    private void setSecondStrike(int secondStrike) {
        this.secondStrike = secondStrike;
    }
}
