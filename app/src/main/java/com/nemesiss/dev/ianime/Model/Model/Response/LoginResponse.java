package com.nemesiss.dev.ianime.Model.Model.Response;

public class LoginResponse extends CommonResponse {
    private String Token;
    private String TokenExpire;

    public String getToken() {
        return Token;
    }

    public String getTokenExpire() {
        return TokenExpire;
    }

    public void setToken(String token) {
        Token = token;
    }

    public void setTokenExpire(String tokenExpire) {
        TokenExpire = tokenExpire;
    }
}
