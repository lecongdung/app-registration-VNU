package com.lecongdung.testvnu.remote.entity;

public class ResponeRegister {

    private User User;
    private String Token;
    private String OTP;

    public ResponeRegister() {
    }

    public ResponeRegister(com.lecongdung.testvnu.remote.entity.User user, String token, String OTP) {
        User = user;
        Token = token;
        this.OTP = OTP;
    }

    public com.lecongdung.testvnu.remote.entity.User getUser() {
        return User;
    }

    public void setUser(com.lecongdung.testvnu.remote.entity.User user) {
        User = user;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }
}
