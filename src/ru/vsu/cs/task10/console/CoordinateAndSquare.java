package ru.vsu.cs.task10.console;

public class CoordinateAndSquare {
    Point firstPoint;
    Point secondPoint;
    Point thirdPoint;
    double square;

    public CoordinateAndSquare(Point firstPoint, Point secondPoint, Point thirdPoint, double square) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
        this.thirdPoint = thirdPoint;
        this.square = square;
    }

    public Point getFirstPoint() {
        return firstPoint;
    }

    public Point getSecondPoint() {
        return secondPoint;
    }

    public Point getThirdPoint() {
        return thirdPoint;
    }

    public double getSquare() {
        return square;
    }
}
