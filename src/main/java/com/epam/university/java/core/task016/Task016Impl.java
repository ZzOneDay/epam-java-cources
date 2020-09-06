package com.epam.university.java.core.task016;

import java.util.ArrayList;
import java.util.Collection;

public class Task016Impl implements Task016 {
    @Override
    public Collection<Coordinate> getSquaresInsideCircle(int radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException();
        }

        int limitMax = radius * 2;
        int limitMin = -(radius * 2);
        ArrayList<Coordinate> list = new ArrayList<>();

        for (int x = limitMin; x < limitMax; x++) {
            for (int y = limitMin; y < limitMax; y++) {
                if (x == 0 || y == 0) {
                    continue;
                }
                Coordinate coordinate = new CoordinateImpl(x, y);
                if (checkResult(coordinate, radius)) {
                    list.add(new CoordinateImpl(x, y));
                }
            }
        }
        return list;
    }


    private boolean checkResult(Coordinate coordinate, int radius) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        radius = radius * 2;
        return ((x * x + y * y) - radius * radius) < 0;
    }
}
