package com.nemesiss.dev.ianime.Model.Model.Request;

public class LoginAndRegisterAccountInfo {
    private String phone;
    private String password;
    public int type=0; //0为正常登陆，1为自动登录

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    public LoginAndRegisterAccountInfo(String phone, String password) {
//        this.phone = phone;
//        this.password = password;
//    }
}
