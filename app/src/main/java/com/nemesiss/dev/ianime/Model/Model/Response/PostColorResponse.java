package com.nemesiss.dev.ianime.Model.Model.Response;

public class PostColorResponse extends CommonResponse {
    private String Receipt;
    private String ImageID;

    public String getImageID() {
        return ImageID;
    }

    public String getReceipt() {
        return Receipt;
    }

    public void setImageID(String imageID) {
        ImageID = imageID;
    }

    public void setReceipt(String receipt) {
        Receipt = receipt;
    }
}
