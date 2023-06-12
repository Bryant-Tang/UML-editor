package main;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Dimension;

public class Calculate {
    public static final Point ZERO_POINT = new Point(0, 0);

    private Calculate() {
    }

    /**
     * half the integer
     * 
     * @param a the integer to be half
     * @return the result of half a
     */
    public static int half(int a) {
        return a / 2;
    }

    /**
     * add two Point
     * 
     * @param a
     * @param b
     * @return the result Point of adding a and b
     */
    public static Point addTwoPoint(Point a, Point b) {
        return new Point(a.x + b.x, a.y + b.y);
    }

    /**
     * calculate the distance between two Point
     * 
     * @param a
     * @param b
     * @return the distance between a and b
     */
    public static double calculateDistance(Point a, Point b) {
        return Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()));
    }

    /**
     * create rectangle by two Point
     * 
     * @param a
     * @param b
     * @return the rectangle created
     */
    public static Rectangle createRectangle(Point a, Point b) {
        Point upperLeft = new Point(Math.min(a.x, b.x), Math.min(a.y, b.y));
        Dimension size = new Dimension(Math.max(a.x, b.x) - upperLeft.x, Math.max(a.y, b.y) - upperLeft.y);
        return new Rectangle(upperLeft, size);
    }
}
