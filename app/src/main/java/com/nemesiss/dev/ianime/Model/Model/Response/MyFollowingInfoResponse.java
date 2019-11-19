package com.nemesiss.dev.ianime.Model.Model.Response;

public class MyFollowingInfoResponse {
    private String NickName;
    private String UserID;
    private String Avatar;

    public String getAvatar() {
        return Avatar;
    }

    public String getNickName() {
        return NickName;
    }

    public String getUserID() {
        return UserID;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }
}
