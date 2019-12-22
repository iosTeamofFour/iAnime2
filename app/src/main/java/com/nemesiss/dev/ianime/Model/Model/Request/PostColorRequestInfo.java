package com.nemesiss.dev.ianime.Model.Model.Request;

import java.util.List;

public class PostColorRequestInfo {
    private String image;
    List<List<Double>> points;

    public List<List<Double>> getPoints() {
        return points;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPoints(List<List<Double>> points) {
        this.points = points;
    }
}
