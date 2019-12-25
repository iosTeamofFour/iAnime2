package com.nemesiss.dev.ianime.Model.Model.Request;

import android.graphics.Point;

import java.util.List;

public class PostColorRequestInfo {
    private String image;
    List<Point> points;

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public PostColorRequestInfo(String image, List<Point> points) {
        this.image = image;
        this.points = points;
    }
}
