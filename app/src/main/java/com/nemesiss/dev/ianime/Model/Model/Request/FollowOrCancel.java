package com.nemesiss.dev.ianime.Model.Model.Request;

public class FollowOrCancel {
    private String UserID;
    private Boolean Cancel;

    public String getUserID() {
        return UserID;
    }

    public Boolean getCancel() {
        return Cancel;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public void setCancel(Boolean cancel) {
        Cancel = cancel;
    }
}
