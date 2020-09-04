package com.epam.university.java.core.task015;

public class OtherLineFunction extends Function {
    // Ay + Bx + C = 0;
    private double indexA;
    private double indexB;
    private double indexC;

    private double[] maxAndMinValueOfX;

    public OtherLineFunction(Point point1, Point point2) {
        double[] indexesFunctions = getIndexes(point1, point2);
        indexA = indexesFunctions[0];
        indexB = indexesFunctions[1];
        indexC = indexesFunctions[2];
        maxAndMinValueOfX = new double[]{point1.getX(), point2.getX()};
    }

    public OtherLineFunction(double[] indexesFunctions, double[] maxAndMinValueOfX) {
        indexA = indexesFunctions[0];
        indexB = indexesFunctions[1];
        indexC = indexesFunctions[2];
        this.maxAndMinValueOfX = maxAndMinValueOfX;
    }

    @Override
    Double getValueY(double indexX) {
        // Ay = -Bx - C;
        // y  = (-Bx - C)/A
        if (!isCorrectValueX(indexX)) {
            return null;
        }
        //-(indexA/indexB) +"x + " + -(indexC/indexB)
        return (-(indexA / indexB) * indexX) - (indexC / indexB);
    }

    double getValueX(double indexY) {
        // Ay + Bx + C = 0;
        // x = (-Ay - C)/B
        return (((-indexA * indexY) - indexC)) / indexB;
    }

    public OtherLineFunction getPerpendicularFunction(Point centerPoint) {
        //This function only for created Rhombus;
        double centerPointX = centerPoint.getX();
        double centerPointY = centerPoint.getY();

        double newIndexC = indexB * centerPointX - indexA * centerPointY;
        double newIndexB = -indexB;

        return new OtherLineFunction(new double[]{indexA, newIndexB, newIndexC}, new double[]{-100, 100});
    }

    private double[] getIndexes(Point point1, Point point2) {
        // Ay + Bx + C = 0; - > y = kx+b => y = -bx/a - c/a;
        double x1 = point1.getX();
        double y1 = point1.getY();

        double x2 = point2.getX();
        double y2 = point2.getY();

        double indexA = y2 - y1;
        double indexB = x1 - x2;

        double indexC = ((x2 * y1) - (x1 * y2));
//        double indexFk = -(indexA/indexB);
//        double indexFb = -(indexC/indexB);
        return new double[]{indexA, indexB, indexC};
    }


    private boolean isCorrectValueX(double indexX) {
        boolean test1 = maxAndMinValueOfX[0] <= indexX && indexX <= maxAndMinValueOfX[1];
        boolean test2 = maxAndMinValueOfX[0] >= indexX && indexX >= maxAndMinValueOfX[1];
        if (test1 || test2) {
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "y = " + -(indexA / indexB) + "x + " + -(indexC / indexB);
    }
}
