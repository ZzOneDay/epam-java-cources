package com.epam.university.java.core.task065;

import java.time.LocalDate;
import java.util.Map;

public class WayTableImpl implements WayTable {
    private Map<LocalDate, Double> map;
    private int countWays;
    private int allDistance;


    /**
     * WayTable.
     * Table that has information about all way in month by employee.
     * After parsing HTML file, created map, countWay and counted all distance.
     * This is args for new WayTable.
     * You can use method to find information.
     *
     * @param map         information value of way by date.
     * @param countWays   sum all values of ways.
     * @param allDistance all distance in month.
     */
    public WayTableImpl(Map<LocalDate, Double> map, int countWays, int allDistance) {
        this.map = map;
        this.countWays = countWays;
        this.allDistance = allDistance;
    }

    public int getDistOfDate(LocalDate localDate) {
        double size = map.get(localDate);
        return (int) Math.round(size);
    }

    public int getAllDistance() {
        return allDistance;
    }


    public int getCountWays() {
        return countWays;
    }
}
