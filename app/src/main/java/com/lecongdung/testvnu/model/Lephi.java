package com.lecongdung.testvnu.model;

public class Lephi {
    private int Id;
    private String username;
    private String Makythi;
    private String Diemthi;
    private String Lephidangky;
    private String Lephidanop;
    private String Lephichuyen;
    private String Nguoithu;
    private String Ngaythu;
    private String status;
    private String Ngaydangky;
    private String Codedangky;
    private String bill4gateway;
    private String createdAt;
    private String updatedAt;

    public Lephi() {
    }

    public Lephi(int id, String username, String makythi, String diemthi, String lephidangky, String lephidanop, String lephichuyen, String nguoithu, String ngaythu, String status, String ngaydangky, String codedangky, String bill4gateway, String createdAt, String updatedAt) {
        Id = id;
        this.username = username;
        Makythi = makythi;
        Diemthi = diemthi;
        Lephidangky = lephidangky;
        Lephidanop = lephidanop;
        Lephichuyen = lephichuyen;
        Nguoithu = nguoithu;
        Ngaythu = ngaythu;
        this.status = status;
        Ngaydangky = ngaydangky;
        Codedangky = codedangky;
        this.bill4gateway = bill4gateway;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMakythi() {
        return Makythi;
    }

    public void setMakythi(String makythi) {
        Makythi = makythi;
    }

    public String getDiemthi() {
        return Diemthi;
    }

    public void setDiemthi(String diemthi) {
        Diemthi = diemthi;
    }

    public String getLephidangky() {
        return Lephidangky;
    }

    public void setLephidangky(String lephidangky) {
        Lephidangky = lephidangky;
    }

    public String getLephidanop() {
        return Lephidanop;
    }

    public void setLephidanop(String lephidanop) {
        Lephidanop = lephidanop;
    }

    public String getLephichuyen() {
        return Lephichuyen;
    }

    public void setLephichuyen(String lephichuyen) {
        Lephichuyen = lephichuyen;
    }

    public String getNguoithu() {
        return Nguoithu;
    }

    public void setNguoithu(String nguoithu) {
        Nguoithu = nguoithu;
    }

    public String getNgaythu() {
        return Ngaythu;
    }

    public void setNgaythu(String ngaythu) {
        Ngaythu = ngaythu;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNgaydangky() {
        return Ngaydangky;
    }

    public void setNgaydangky(String ngaydangky) {
        Ngaydangky = ngaydangky;
    }

    public String getCodedangky() {
        return Codedangky;
    }

    public void setCodedangky(String codedangky) {
        Codedangky = codedangky;
    }

    public String getBill4gateway() {
        return bill4gateway;
    }

    public void setBill4gateway(String bill4gateway) {
        this.bill4gateway = bill4gateway;
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
