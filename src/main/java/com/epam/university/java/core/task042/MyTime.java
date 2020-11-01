package com.epam.university.java.core.task042;

import java.time.LocalTime;

public class MyTime implements Comparable {
    private LocalTime startTime;
    private LocalTime endTime;

    public MyTime(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    static MyTime getBusyTime(String oneTime) {
        String[] parts = oneTime.split("-");
        LocalTime start = getTimeOfString(parts[0]);
        LocalTime end = getTimeOfString(parts[1]);
        return new MyTime(start, end);
    }

    static LocalTime getTimeOfString(String currentTime) {
        String[] endParts = currentTime.split(":");
        return LocalTime.of(Integer.parseInt(endParts[0]),
                Integer.parseInt(endParts[1]));
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }


    @Override
    public int compareTo(Object o) {
        MyTime otherTime = (MyTime) o;
        if (this.getStartTime().isBefore(otherTime.getStartTime())) {
            return -1;
        } else {
            return 1;
        }
    }
}
