package com.lecongdung.testvnu.model;

public class MyKyThi {
    private int Id;
    private String MaKythi;
    private String TenKythi;
    private String Mota;
    private String Tungay;
    private String Toingay;
    private int Socathi;
    private String Handangky;
    private String Lephidangky;
    private String Lephidanop;
    private String Nguoithu;
    private String Ngaythu;
    private String status;
    private String Ngaydangky;

    public MyKyThi() {
    }

    public MyKyThi(int id, String maKythi, String tenKythi, String mota, String tungay, String toingay, int socathi, String handangky, String lephidangky, String lephidanop, String nguoithu, String ngaythu, String status, String ngaydangky) {
        Id = id;
        MaKythi = maKythi;
        TenKythi = tenKythi;
        Mota = mota;
        Tungay = tungay;
        Toingay = toingay;
        Socathi = socathi;
        Handangky = handangky;
        Lephidangky = lephidangky;
        Lephidanop = lephidanop;
        Nguoithu = nguoithu;
        Ngaythu = ngaythu;
        this.status = status;
        Ngaydangky = ngaydangky;
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
}
