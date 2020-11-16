package com.epam.university.java.core.task065;

import java.time.LocalDate;

class Way {
    private int index;
    private double dist;
    private String comment;
    private LocalDate date;

    public Way(int index, double dist, String comment, LocalDate date) {
        this.index = index;
        this.dist = dist;
        this.comment = comment;
        this.date = date;
    }

    double getDist() {
        return getCorrectDist();
    }

    LocalDate getData() {
        return date;
    }

    private double getCorrectDist() {
        if (comment.length() != 0) {
            String[] strings = comment.split(",");
            String distSting = strings[strings.length - 1].replaceAll("[^0-9]", "").trim();
            dist = Double.parseDouble(distSting);
            return dist;
        }
        return dist;
    }
}
