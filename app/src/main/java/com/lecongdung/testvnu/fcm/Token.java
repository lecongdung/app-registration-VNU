package com.lecongdung.testvnu.fcm;

public class Token {
    private String token;
    private String type;

    public Token(String token, String type) {
        this.token = token;
        this.type = type;
    }

    public Token() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
