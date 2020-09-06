package com.epam.university.java.core.task015;

/**
 * Line function of y = kx + b.
 * <p>
 * If point1 and point2 haven't one indexX or IndexY,
 * point1.x != point2.x and point1.y != point2y.
 * So, this function line has incline
 * </p>
 * <p>
 * General idea of this class is create new Function used Point1 and Point2.
 * Find indexA,indexB,indexC for new Line function.
 * Be ready find valueY of indexX of this function, as f(x) = kx + b.
 * </p>
 *
 * @author Pavel_Novikov
 * @since 1.0
 */
public class OtherLineLineFunction implements Function {
    // Ay + Bx + C = 0;
    private double indexA;
    private double indexB;
    private double indexC;
    private double[] maxAndMinValueOfX;

    /**
     * Create new Function used 2 points.
     * <p>
     * when we are creating new Function,
     * constructor uses {@link OtherLineLineFunction#getIndexes(Point, Point)}
     * and setValueLimit for this function by Points.
     * </p>
     *
     * @param point1 point of square for find function
     * @param point2 other point of square for find function
     */
    OtherLineLineFunction(Point point1, Point point2) {
        double[] indexesFunctions = getIndexes(point1, point2);
        indexA = indexesFunctions[0];
        indexB = indexesFunctions[1];
        indexC = indexesFunctions[2];
        maxAndMinValueOfX = new double[]{point1.getX(), point2.getX()};
    }

    /**
     * Other private constructor.
     * <p>
     * Another constructor for creating a perpendicular, for {@link Rhombus},
     * and later this function was used to find a new point,
     * and after that, this function was not used.
     * </p>
     *
     * @param indexesFunctions  arrays index,
     *                          that was created
     *                          {@link OtherLineLineFunction#getIndexes(Point, Point)}
     * @param maxAndMinValueOfX this is local values, very bit limits for this task,
     *                          but you can change this for double distance
     *                          between point1 and Central point.
     */
    private OtherLineLineFunction(double[] indexesFunctions, double[] maxAndMinValueOfX) {
        indexA = indexesFunctions[0];
        indexB = indexesFunctions[1];
        indexC = indexesFunctions[2];
        this.maxAndMinValueOfX = maxAndMinValueOfX;
    }

    @Override
    public Double getValueY(double indexX) {
        // Ay = -Bx - C;
        // y  = (-Bx - C)/A
        if (!isCorrectValueX(indexX)) {
            return null;
        }
        //-(indexA/indexB) +"x + " + -(indexC/indexB)
        return (-(indexA / indexB) * indexX) - (indexC / indexB);
    }

    @Deprecated
    double getValueX(double indexY) {
        // Ay + Bx + C = 0;
        // x = (-Ay - C)/B
        //If you want use this method you need ask yourself, Are you seriously?
        return (((-indexA * indexY) - indexC)) / indexB;
    }

    /**
     * Find perpendicular of function.
     * <p>
     * This method used only for created {@link Rhombus},
     * and default limit -100 and 100,
     * but when is finding new point,
     * max value is distance between Point1 and CentralPoint multiply x2.
     * Task, has point, and point max value x ~ 10, and y ~ 10.
     * in Future, new Function of Square, have limit of new Points,
     * points of Rhombus, not these values of limit.
     * </p>
     *
     * @param centerPoint point between Point1 and Point2,
     *                    this is centralPoint and for perpendicular.
     * @return new Function that is perpendicular of old function.
     */
    OtherLineLineFunction getPerpendicularFunction(Point centerPoint) {
        double centerPointX = centerPoint.getX();
        double centerPointY = centerPoint.getY();

        double newIndexC = indexB * centerPointX - indexA * centerPointY;
        double newIndexB = -indexB;

        double[] valueFunction = new double[]{indexA, newIndexB, newIndexC};
        double[] valuesOfLimit = new double[]{-100, 100};

        return new OtherLineLineFunction(valueFunction, valuesOfLimit);
    }

    /**
     * Find indexes of LineFunction.
     * <p>
     * If you want create new Function,
     * used points, you can find line function.
     * Ay + Bx + C = 0.
     * </p>
     *
     * @param point1 - point1.
     * @param point2 - point2.
     * @return indexes for line function by points.
     */
    private double[] getIndexes(Point point1, Point point2) {
        double x1 = point1.getX();
        double y1 = point1.getY();

        double x2 = point2.getX();
        double y2 = point2.getY();

        double indexA = y2 - y1;
        double indexB = x1 - x2;

        double indexC = ((x2 * y1) - (x1 * y2));
        return new double[]{indexA, indexB, indexC};
    }


    /**
     * Check indexX.
     * <p>
     * Very important thing of solution this functions
     * You have function, and you can find valueX by indexX, but,
     * our square have limit, for example point1(1,1), point2 (4,4).
     * When you index is 2, you can find valueY,
     * but if you try to find the index over limit (1 - 4),
     * you can't because square has final points.
     * </p>
     *
     * @param indexX - our indexX, used this for will find valueY
     * @return result of check valueX, correct on incorrect valueX.
     */
    private boolean isCorrectValueX(double indexX) {
        boolean test1 = maxAndMinValueOfX[0] <= indexX && indexX <= maxAndMinValueOfX[1];
        boolean test2 = maxAndMinValueOfX[0] >= indexX && indexX >= maxAndMinValueOfX[1];
        return test1 || test2;
    }


    @Override
    public String toString() {
        return "y = " + -(indexA / indexB) + "x + " + -(indexC / indexB);
    }
}
