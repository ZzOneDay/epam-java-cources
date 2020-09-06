package com.epam.university.java.core.task019;

public class RobotImpl implements Robot {
    private RobotPosition startPosition;
    private RobotPosition robotPosition;
    private int robotDirection;


    /**
     * Constructor Robots.
     * <p>
     * When Robot created, Robot has position 0.0,
     * and direction number 1. Robot has 4 directions.
     * When robot is turning, index direction is checking,
     * if direction 0, it will change to 4,
     * and when direction 5, it will change to 1,
     * </p>
     */
    public RobotImpl() {
        this.startPosition = new RobotPositionImpl(0, 0);
        robotPosition = startPosition;
        robotDirection = 1;
    }

    @Override
    public RobotPosition getPosition() {
        return robotPosition;
    }

    @Override
    public void setPosition(RobotPosition position) {
        robotPosition = position;
    }

    @Override
    public void invokeAction(RobotCommand command) {
        if (command.equals(RobotCommand.TURN_LEFT)) {
            robotDirection = getCorrectDirection(--robotDirection);
        }

        if (command.equals(RobotCommand.TURN_RIGHT)) {
            robotDirection = getCorrectDirection(++robotDirection);
        }

        if (command.equals(RobotCommand.MOVE_FORWARD)) {
            RobotPosition robotPosition = getNewPosition(getPosition(), robotDirection);
            setPosition(robotPosition);
        }
    }

    RobotPosition getStartPosition() {
        return startPosition;
    }

    private RobotPosition getNewPosition(RobotPosition robotPosition, int robotDirection) {
        int x = robotPosition.getX();
        int y = robotPosition.getY();
        if (robotDirection == 1) {
            y++;
        }
        if (robotDirection == 2) {
            x++;
        }
        if (robotDirection == 3) {
            y--;
        }
        if (robotDirection == 4) {
            x--;
        }
        return new RobotPositionImpl(x, y);
    }

    private int getCorrectDirection(int robotDirection) {
        if (robotDirection == 0) {
            robotDirection = 4;
        }
        if (robotDirection == 5) {
            robotDirection = 1;
        }
        return robotDirection;
    }
}
