package com.lecongdung.testvnu.model;

public class Lephi {
    private int id;
    private String tendangnhap;
    private String makythi;
    private String diemthi;
    private String lephidangky;
    private String lephidanop;
    private String nguoithu;
    private String ngaythu;
    private int status;
    private String ngaydangky;
    private String codedangky;

    public Lephi() {
    }

    public Lephi(int id, String tendangnhap, String makythi, String diemthi, String lephidangky, String lephidanop, String nguoithu, String ngaythu, int status, String ngaydangky, String codedangky) {
        this.id = id;
        this.tendangnhap = tendangnhap;
        this.makythi = makythi;
        this.diemthi = diemthi;
        this.lephidangky = lephidangky;
        this.lephidanop = lephidanop;
        this.nguoithu = nguoithu;
        this.ngaythu = ngaythu;
        this.status = status;
        this.ngaydangky = ngaydangky;
        this.codedangky = codedangky;
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

    public String getMakythi() {
        return makythi;
    }

    public void setMakythi(String makythi) {
        this.makythi = makythi;
    }

    public String getDiemthi() {
        return diemthi;
    }

    public void setDiemthi(String diemthi) {
        this.diemthi = diemthi;
    }

    public String getLephidangky() {
        return lephidangky;
    }

    public void setLephidangky(String lephidangky) {
        this.lephidangky = lephidangky;
    }

    public String getLephidanop() {
        return lephidanop;
    }

    public void setLephidanop(String lephidanop) {
        this.lephidanop = lephidanop;
    }

    public String getNguoithu() {
        return nguoithu;
    }

    public void setNguoithu(String nguoithu) {
        this.nguoithu = nguoithu;
    }

    public String getNgaythu() {
        return ngaythu;
    }

    public void setNgaythu(String ngaythu) {
        this.ngaythu = ngaythu;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNgaydangky() {
        return ngaydangky;
    }

    public void setNgaydangky(String ngaydangky) {
        this.ngaydangky = ngaydangky;
    }

    public String getCodedangky() {
        return codedangky;
    }

    public void setCodedangky(String codedangky) {
        this.codedangky = codedangky;
    }
}
