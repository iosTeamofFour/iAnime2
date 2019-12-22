package com.nemesiss.dev.ianime.Model.Model.Request;

public class GetMyWorkRequestInfo {
    private int userid;
    private String type;
    private Boolean top;

    public Boolean getTop() {
        return top;
    }

    public int getUserid() {
        return userid;
    }

    public String getType() {
        return type;
    }

    public void setTop(Boolean top) {
        this.top = top;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
