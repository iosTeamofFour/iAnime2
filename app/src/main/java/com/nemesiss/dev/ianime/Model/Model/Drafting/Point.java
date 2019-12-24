package com.nemesiss.dev.ianime.Model.Model.Drafting;

public class Point {

    private int X;
    private int Y;


    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public double getPercentageX(double CanvaWidth) {
        return (double)X / CanvaWidth;
    }

    public double getPercentageY(double CanvaHeight) {
        return (double)Y / CanvaHeight;
    }

    public Point(int x, int y) {
        X = x;
        Y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Point) {
            Point right = (Point) obj;
            return X == right.X && Y == right.Y;
        }
        return false;
    }
}
