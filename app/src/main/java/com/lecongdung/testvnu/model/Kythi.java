package com.lecongdung.testvnu.model;

import java.io.Serializable;

public class Kythi implements Serializable {
    private int Id;
    private String MaKythi;
    private String TenKythi;
    private String Mota;
    private String Tungay;
    private String Toingay;
    private int Socathi;
    private String Handangky;
    private int Trangthai;
    private int Taocathi;
    private String anhkythi;
    private String createdAt;
    private String updatedAt;


    public Kythi() {
    }

    public Kythi(int id, String maKythi, String tenKythi, String mota, String tungay, String toingay, int socathi, String handangky, int trangthai, int taocathi, String anhkythi, String createdAt, String updatedAt) {
        Id = id;
        MaKythi = maKythi;
        TenKythi = tenKythi;
        Mota = mota;
        Tungay = tungay;
        Toingay = toingay;
        Socathi = socathi;
        Handangky = handangky;
        Trangthai = trangthai;
        Taocathi = taocathi;
        this.anhkythi = anhkythi;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getMaKythi() {
        return MaKythi;
    }

    public void setMaKythi(String maKythi) {
        MaKythi = maKythi;
    }

    public String getTenKythi() {
        return TenKythi;
    }

    public void setTenKythi(String tenKythi) {
        TenKythi = tenKythi;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }

    public String getTungay() {
        return Tungay;
    }

    public void setTungay(String tungay) {
        Tungay = tungay;
    }

    public String getToingay() {
        return Toingay;
    }

    public void setToingay(String toingay) {
        Toingay = toingay;
    }

    public int getSocathi() {
        return Socathi;
    }

    public void setSocathi(int socathi) {
        Socathi = socathi;
    }

    public String getHandangky() {
        return Handangky;
    }

    public void setHandangky(String handangky) {
        Handangky = handangky;
    }

    public int getTrangthai() {
        return Trangthai;
    }

    public void setTrangthai(int trangthai) {
        Trangthai = trangthai;
    }

    public int getTaocathi() {
        return Taocathi;
    }

    public void setTaocathi(int taocathi) {
        Taocathi = taocathi;
    }

    public String getAnhkythi() {
        return anhkythi;
    }

    public void setAnhkythi(String anhkythi) {
        this.anhkythi = anhkythi;
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
