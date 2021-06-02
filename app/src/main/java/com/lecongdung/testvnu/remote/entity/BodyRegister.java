package com.lecongdung.testvnu.remote.entity;
public class BodyRegister {
    private String tendangnhap;
    private String Email;
    private String password;
    private boolean verify;


    public BodyRegister() {
    }

    public BodyRegister(String tendangnhap, String email, String password, boolean verify) {
        this.tendangnhap = tendangnhap;
        Email = email;
        this.password = password;
        this.verify = verify;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isVerify() {
        return verify;
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
    }
}
