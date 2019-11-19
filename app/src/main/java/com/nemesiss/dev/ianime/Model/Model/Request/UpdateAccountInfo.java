package com.nemesiss.dev.ianime.Model.Model.Request;

public class UpdateAccountInfo {
    private String NickName;
    private String Signature;
    private String Avatar;
    private String BackgroundPhoto;

    public String getSignature() {
        return Signature;
    }

    public String getNickName() {
        return NickName;
    }

    public String getBackgroundPhoto() {
        return BackgroundPhoto;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public void setBackgroundPhoto(String backgroundPhoto) {
        BackgroundPhoto = backgroundPhoto;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }
}
