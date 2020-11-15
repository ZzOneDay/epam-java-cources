package com.epam.university.java.core.task057;

import java.util.HashMap;

public class Task057Impl implements Task057 {
    private HashMap<Integer, Integer> windowOfType = new HashMap<>();

    {
        windowOfType.put(7, 2);
        windowOfType.put(8, 4);
        windowOfType.put(1, 6);
        windowOfType.put(2, 8);

        windowOfType.put(3, 2);
        windowOfType.put(4, 4);
        windowOfType.put(5, 6);
        windowOfType.put(6, 8);
    }


    @Override
    public Window getWindowForDelivery(int level, int entrances, int numberOfFlat) {
        int startEntrance = 1;
        int startLevel = 1;
        int targetNumber = 1;

        for (int e = startEntrance; e <= entrances; e++) {
            for (int l = startLevel; l <= level; l++) {
                for (int type = 1; type < 9; type++) {
                    if (targetNumber == numberOfFlat) {
                        SideType side = findSide(type);
                        int numberOfWindow = getNumberOfWindow(type, e, entrances);
                        return new WindowImpl(l, numberOfWindow, side);
                    }
                    targetNumber++;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    private SideType findSide(int type) {
        if (type == 7 || type == 8 || type == 1 || type == 2) {
            return SideType.FRONT_SIDE;
        } else {
            return SideType.BACK_SIDE;
        }
    }

    private int getNumberOfWindow(int type, int entrance, int entrancesInHouse) {
        int windowNumber;
        SideType side = findSide(type);
        if (side.equals(SideType.FRONT_SIDE)) {
            windowNumber = (entrance - 1) * 8;
            windowNumber += windowOfType.get(type);
        } else {
            int backEntrance = entrancesInHouse - entrance;
            windowNumber = backEntrance * 8;
            windowNumber += windowOfType.get(type);
        }
        return windowNumber;
    }
}
