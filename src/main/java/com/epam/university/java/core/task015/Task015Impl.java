package com.epam.university.java.core.task015;

public class Task015Impl implements Task015 {
    @Override
    public double getArea(Square first, Square second) {
        SquareImpl square1 = (SquareImpl) first;
        SquareImpl square2 = (SquareImpl) second;

        VectorSquare vSquare1 = square1.getVectorSquare();
        VectorSquare vSquare2 = square2.getVectorSquare();

        VectorCalculator vectorCalculator = new VectorCalculator();
        double square = 0;
        double step = 0.01;
        for (double x = 0.0; x < 10; x = x + step) {
            Double[] values = vectorCalculator.getValueOfIntersection(vSquare1, vSquare2, x);
            if (values != null) {
                double miniSquare = (Math.abs(values[0] - values[1])) * step;
                square += miniSquare;
            }
        }
        square = ((int) (square * 10)) / 10.0;
        return square;
    }
}
