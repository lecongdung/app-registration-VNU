package com.lecongdung.testvnu.remote.entity;

public class ResponeStudentUpdate {
    private int id;
    private String tendangnhap;
    private String HoTen;
    private String Sodienthoai;
    private String Email;
    private String Trangthai;
    private String password;
    private int verify;
    private String createdAt;
    private String updatedAt;

    public ResponeStudentUpdate() {
    }

    public ResponeStudentUpdate(int id, String tendangnhap, String hoTen, String sodienthoai, String email, String trangthai, String password, int verify, String createdAt, String updatedAt) {
        this.id = id;
        this.tendangnhap = tendangnhap;
        HoTen = hoTen;
        Sodienthoai = sodienthoai;
        Email = email;
        Trangthai = trangthai;
        this.password = password;
        this.verify = verify;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getSodienthoai() {
        return Sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        Sodienthoai = sodienthoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTrangthai() {
        return Trangthai;
    }

    public void setTrangthai(String trangthai) {
        Trangthai = trangthai;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getVerify() {
        return verify;
    }

    public void setVerify(int verify) {
        this.verify = verify;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
