package com.lecongdung.testvnu.model;

public class Diemthi {
    private int id;
    private String makythi;
    private String madiemthi;
    private String ghichu;


    public Diemthi() {
    }

    public Diemthi(int id, String makythi, String madiemthi, String ghichu) {
        this.id = id;
        this.makythi = makythi;
        this.madiemthi = madiemthi;
        this.ghichu = ghichu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMakythi() {
        return makythi;
    }

    public void setMakythi(String makythi) {
        this.makythi = makythi;
    }

    public String getMadiemthi() {
        return madiemthi;
    }

    public void setMadiemthi(String madiemthi) {
        this.madiemthi = madiemthi;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
}
