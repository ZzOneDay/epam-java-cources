package com.epam.university.java.core.task040;

import java.util.ArrayList;

public class Frame implements FrameInterface {
    private FrameType frameType;
    private int id;
    private int firstStrike;
    private int secondStrike;

    static FrameInterface getInstance(int id, String message) {
        if (message.length() == 3) {
            return FinalityFrame.getFinalityFrame(message);
        }

        if (message.contains("X")) {
            Frame frame = new Frame(id, FrameType.STRIKE);
            frame.setFirstStrike(10);
            return frame;
        }

        if (message.contains("/")) {
            Frame frame = new Frame(id, FrameType.SPLIT);
            int firstStrike = Integer.parseInt(message.charAt(0) + "");
            frame.setFirstStrike(firstStrike);
            frame.setSecondStrike(10 - firstStrike);
            return frame;
        }

        Frame frame = new Frame(id, FrameType.SIMPLE);
        int[] values = scoreOfMessage(message);
        frame.setFirstStrike(values[0]);
        frame.setSecondStrike(values[1]);
        return frame;
    }

    private Frame(int id, FrameType frameType) {
        this.frameType = frameType;
        this.id = id;
    }


    private void setSecondStrike(int secondStrike) {
        this.secondStrike = secondStrike;
    }

    private static int[] scoreOfMessage(String message) {
        String[] messages = message.split("");
        return new int[]{Integer.parseInt(messages[0]),
                Integer.parseInt(messages[1])};
    }


    @Override
    public int getScore(ArrayList<FrameInterface> frames) {
        int score = 0;
        if (frameType.equals(FrameType.SIMPLE)) {
            score = getSimpleScore();
        }

        if (frameType.equals(FrameType.SPLIT)) {
            score = getSplitScore(frames);
        }

        if (frameType.equals(FrameType.STRIKE)) {
            score = getStrikeScore(frames);
        }
        return score;
    }

    private int getSimpleScore() {
        return firstStrike + secondStrike;
    }

    private int getSplitScore(ArrayList<FrameInterface> frames) {
        int idNext = id + 1;
        FrameInterface nextFrame = frames.get(idNext);
        return firstStrike + secondStrike + nextFrame.getFirstStrike();
    }

    private int getStrikeScore(ArrayList<FrameInterface> frames) {
        int nextId = id + 1;
        FrameInterface nextFrame = frames.get(nextId);
        if (frameType.equals(FrameType.SIMPLE)) {
            return firstStrike + nextFrame.getFirstStrike() + nextFrame.getSecondStrike();
        }

        if (frameType.equals(FrameType.SPLIT)) {
            return firstStrike + 10;
        }

        if (nextFrame.getFrameType().equals(FrameType.STRIKE)) {
            int nextNextId = nextId + 1;
            FrameInterface nextNextFrame = frames.get(nextNextId);
            return firstStrike + nextFrame.getFirstStrike() + nextNextFrame.getFirstStrike();
        }

        return firstStrike + nextFrame.getFirstStrike() + nextFrame.getSecondStrike();
    }

    @Override
    public FrameType getFrameType() {
        return frameType;
    }

    @Override
    public int getFirstStrike() {
        return firstStrike;
    }

    private void setFirstStrike(int firstStrike) {
        this.firstStrike = firstStrike;
    }

    @Override
    public int getSecondStrike() {
        return secondStrike;
    }
}
