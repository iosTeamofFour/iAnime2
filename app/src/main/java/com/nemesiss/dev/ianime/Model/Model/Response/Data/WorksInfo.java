package com.nemesiss.dev.ianime.Model.Model.Response.Data;

import java.util.Date;

public class WorksInfo {
    private int imageID;
    private String name;
    private String date;

    public  WorksInfo(int imageID,String name,String date)
    {
        this.imageID=imageID;
        this.name=name;
        this.date=date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public int getImageID() {
        return imageID;
    }

    public String getName() {
        return name;
    }
}
