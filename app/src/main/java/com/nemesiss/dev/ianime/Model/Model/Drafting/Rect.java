package com.nemesiss.dev.ianime.Model.Model.Drafting;

public class Rect {
    private float left;
    private float top;
    private int color;
    private float right;
    private float bottom;


    public Rect(float left,float top,float right,float bottom,int color)
    {
        this.left=left;
        this.top=top;
        this.right=right;
        this.bottom=bottom;
        this.color=color;
    }
    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }


}
