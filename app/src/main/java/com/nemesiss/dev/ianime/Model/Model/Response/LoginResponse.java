package com.nemesiss.dev.ianime.Model.Model.Response;

public class LoginResponse extends CommonResponse {
    private String Token;
    private int TokenExpire;

    public String getToken() {
        return Token;
    }

    public int getTokenExpire() {
        return TokenExpire;
    }

    public void setToken(String token) {
        Token = token;
    }

    public void setTokenExpire(int tokenExpire) {
        TokenExpire = tokenExpire;
    }
}
