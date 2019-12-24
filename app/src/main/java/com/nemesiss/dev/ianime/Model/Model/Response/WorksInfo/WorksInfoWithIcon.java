package com.nemesiss.dev.ianime.Model.Model.Response.WorksInfo;

public class WorksInfoWithIcon extends WorksInfo {
    private String authorName;
    public WorksInfoWithIcon(int imageID,String name,String date,String authorName)
    {
        super(imageID,name,date);
        this.authorName=authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }
}
