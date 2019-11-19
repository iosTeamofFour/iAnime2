package com.nemesiss.dev.ianime.Model.Model.Response;

public class AccountInfoResponse extends CommonResponse {
    private String NickName;
    private String Avatar;
    private String BackgroundPhoto;
    private String Signature;
    private int Follower;
    private int Following;
    private String Rank;

    public String getAvatar() {
        return Avatar;
    }

    public String getBackgroundPhoto() {
        return BackgroundPhoto;
    }

    public int getFollower() {
        return Follower;
    }

    public int getFollowing() {
        return Following;
    }

    public String getNickName() {
        return NickName;
    }

    public String getRank() {
        return Rank;
    }

    public String getSignature() {
        return Signature;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public void setBackgroundPhoto(String backgroundPhoto) {
        BackgroundPhoto = backgroundPhoto;
    }

    public void setFollower(int follower) {
        Follower = follower;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public void setFollowing(int following) {
        Following = following;
    }

    public void setRank(String rank) {
        Rank = rank;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }

}
