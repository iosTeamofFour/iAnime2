package com.nemesiss.dev.ianime.Model.Model.Response;

public class QueryColorProgressResponse extends CommonResponse {
    private String ImageID;
    private String url;

    public String getImageID() {
        return ImageID;
    }

    public String getUrl() {
        return url;
    }

    public void setImageID(String imageID) {
        ImageID = imageID;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
