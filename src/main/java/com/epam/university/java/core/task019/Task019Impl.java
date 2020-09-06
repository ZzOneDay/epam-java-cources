package com.epam.university.java.core.task019;

public class Task019Impl implements Task019 {
    @Override
    public void invokeAction(Robot robot, RobotCommand command) {
        robot.invokeAction(command);
    }

    @Override
    public boolean isOnStartPosition(Robot robot) {
        if (robot == null) {
            throw new IllegalArgumentException();
        }
        RobotPosition robotPosition = robot.getPosition();
        RobotImpl robot1 = (RobotImpl) robot;
        RobotPosition robotPositionStart = robot1.getStartPosition();
        return robotPosition.equals(robotPositionStart);
    }
}
