package com.nemesiss.dev.ianime.Model.Model.Drafting;

public class ColorizeTask {
    private int CreatedAt;
    private String Receipt;
    private boolean Finished;


    public boolean isFinished() {
        return Finished;
    }

    public ColorizeTask setFinished(boolean finished) {
        Finished = finished;
        return this;
    }

    public ColorizeTask(int createdAt, String receipt) {
        CreatedAt = createdAt;
        Receipt = receipt;
        Finished = false;
    }

    public ColorizeTask(long date2UnixStamp) {
    }

    public int getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(int createdAt) {
        CreatedAt = createdAt;
    }

    public String getReceipt() {
        return Receipt;
    }

    public void setReceipt(String receipt) {
        Receipt = receipt;
    }
}
