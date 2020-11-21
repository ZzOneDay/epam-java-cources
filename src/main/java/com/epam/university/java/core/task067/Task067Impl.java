package com.epam.university.java.core.task067;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Task067Impl implements Task067 {
    @Override
    public int knightMovements(int x1, int y1, int x2, int y2) {
        Position start = new Position(x1, y1);
        Position finish = new Position(x2, y2);
        List<Position> variantsOfStep = getVariantsOfStep(start);

        int countStep = 1;

        while (true) {
            for (Position position : variantsOfStep) {
                if (finish.equals(position)) {
                    return countStep;
                }
            }

            for (Position position : variantsOfStep) {
                if (closePositionTest(position, finish)) {
                    return countStep + 1;
                }
            }
            Position newStart = getMoreClosePosition(finish, variantsOfStep);
            variantsOfStep = getVariantsOfStep(newStart);
            countStep++;
        }
    }

    private double getDistBetweenPositions(Position position1, Position position2) {
        int x1 = position1.getX();
        int y1 = position1.getY();
        int x2 = position2.getX();
        int y2 = position2.getY();
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }


    List<Position> getVariantsOfStep(Position startStep) {
        LinkedList<Position> positions = new LinkedList<>();

        //StepY+
        int stepUpY = startStep.getY() + 2;
        int stepUpYRightX = startStep.getX() + 1;
        int stepUpYLeftX = startStep.getX() - 1;
        positions.add(new Position(stepUpYRightX, stepUpY));
        positions.add(new Position(stepUpYLeftX, stepUpY));

        //StepY-
        int stepDownY = startStep.getY() - 2;
        int stepDownYRightX = startStep.getX() + 1;
        int stepDownYLeftX = startStep.getX() - 1;
        positions.add(new Position(stepDownYRightX, stepDownY));
        positions.add(new Position(stepDownYLeftX, stepDownY));


        //StepX-
        int stepLeftX = startStep.getX() - 2;
        int stepLeftXUpY = startStep.getY() + 1;
        int stepLeftXDownY = startStep.getY() - 1;
        positions.add(new Position(stepLeftX, stepLeftXUpY));
        positions.add(new Position(stepLeftX, stepLeftXDownY));

        //StepX+
        int stepRightX = startStep.getX() + 2;
        int stepRightXUpY = startStep.getY() + 1;
        int stepRightXDownY = startStep.getY() - 1;
        positions.add(new Position(stepRightX, stepRightXUpY));
        positions.add(new Position(stepRightX, stepRightXDownY));

        //TestNegative
        Iterator iterator = positions.iterator();
        while (iterator.hasNext()) {
            Position position = (Position) iterator.next();
            if (position.getX() < 1 || position.getY() < 1) {
                iterator.remove();
            }
        }

        return positions;
    }

    private Position getMoreClosePosition(Position positionFinish, List<Position> positions) {
        double minBetween = 10000;
        Position closePosition = null;
        for (Position position : positions) {
            double distBetween = getDistBetweenPositions(positionFinish, position);
            if (distBetween < minBetween) {
                closePosition = position;
                minBetween = distBetween;
            }
        }
        return closePosition;
    }

    boolean closePositionTest(Position position1, Position position2) {
        boolean testX = closePositionTestLineX(position1, position2);
        if (testX) {
            return true;
        }

        boolean testY = closePositionTestLineY(position1, position2);
        if (testY) {
            return true;
        }

        return false;
    }

    private boolean closePositionTestLineY(Position position1, Position position2) {
        boolean closeY = Math.abs(position1.getY() - position2.getY()) == 0;
        boolean closeX = Math.abs(position1.getX() - position2.getX()) == 1;
        return closeY && closeX;
    }

    private boolean closePositionTestLineX(Position position1, Position position2) {
        boolean closeY = Math.abs(position1.getY() - position2.getY()) == 1;
        boolean closeX = Math.abs(position1.getX() - position2.getX()) == 0;
        return closeY && closeX;
    }
}
