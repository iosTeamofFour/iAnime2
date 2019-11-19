package com.nemesiss.dev.ianime.Model.Model.Response;

public class AccountInfoResponse extends CommonResponse {
    private String NickName;
    private String Avatar;
    private String BackgroundPhoto;
    private String Signature;
    private String Follower;
    private String Following;
    private String Rank;

    public String getAvatar() {
        return Avatar;
    }

    public String getBackgroundPhoto() {
        return BackgroundPhoto;
    }

    public String getFollower() {
        return Follower;
    }

    public String getFollowing() {
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

    public void setFollower(String follower) {
        Follower = follower;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public void setFollowing(String following) {
        Following = following;
    }

    public void setRank(String rank) {
        Rank = rank;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }

}
