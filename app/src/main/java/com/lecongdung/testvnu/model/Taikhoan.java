package com.lecongdung.testvnu.model;

public class Taikhoan {
    private int id;
    private String tendangnhap;
    private String hoten;
    private String sodienthoai;
    private String email;
    private int trangthai;
    private String password;
    private String verify;
    private String create_at;
    private String update_at;

    public Taikhoan() {
    }

    public Taikhoan(int id, String tendangnhap, String hoten, String sodienthoai, String email, int trangthai, String password, String verify, String create_at, String update_at) {
        this.id = id;
        this.tendangnhap = tendangnhap;
        this.hoten = hoten;
        this.sodienthoai = sodienthoai;
        this.email = email;
        this.trangthai = trangthai;
        this.password = password;
        this.verify = verify;
        this.create_at = create_at;
        this.update_at = update_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }
}
