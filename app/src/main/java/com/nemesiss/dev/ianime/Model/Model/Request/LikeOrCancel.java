package com.nemesiss.dev.ianime.Model.Model.Request;

public class LikeOrCancel {
    private int id;
    private Boolean Cancel;

    public Boolean getCancel() {
        return Cancel;
    }

    public int getId() {
        return id;
    }

    public void setCancel(Boolean cancel) {
        Cancel = cancel;
    }

    public void setId(int id) {
        this.id = id;
    }
}
