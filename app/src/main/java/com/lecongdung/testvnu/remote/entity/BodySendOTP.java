package com.lecongdung.testvnu.remote.entity;

public class BodySendOTP {
    private String tendangnhap;
    private String Email;
    private boolean verify;

    public BodySendOTP() {
    }

    public BodySendOTP(String tendangnhap, String email, boolean verify) {
        this.tendangnhap = tendangnhap;
        Email = email;
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

    public boolean isVerify() {
        return verify;
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
    }
}
