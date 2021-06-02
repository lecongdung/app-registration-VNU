package com.lecongdung.testvnu.remote.entity;

public class BodyLogin {

    private String tendangnhap;
    private String password;

    public BodyLogin() {
    }

    public BodyLogin(String tendangnhap, String password) {
        this.tendangnhap = tendangnhap;
        this.password = password;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
