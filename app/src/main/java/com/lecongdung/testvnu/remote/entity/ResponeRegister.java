package com.lecongdung.testvnu.remote.entity;

public class ResponeRegister {

    private User user;
    private String Token;


    public ResponeRegister() {
    }

    public ResponeRegister(User user, String token) {
        this.user = user;
        Token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
