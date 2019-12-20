package com.nemesiss.dev.ianime.Model.Model;

import android.graphics.Paint;

public class Circle {

    private float radius;
    private float centerX;
    private float centerY;
    private int color;
  public  Circle(float radius,float centerX,float centerY,int color)
  {
      this.centerX=centerX;
      this.centerY=centerY;
      this.radius=radius;
      this.color=color;
  }

    public float getCenterX() {
        return centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public float getRadius() {
        return radius;
    }

    public int getColor() {
        return color;
    }



    public void setCenterX(float centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(float centerY) {
        this.centerY = centerY;
    }

    public void setColor(int color) {
        this.color = color;
    }



    public void setRadius(float radius) {
        this.radius = radius;
    }
}
