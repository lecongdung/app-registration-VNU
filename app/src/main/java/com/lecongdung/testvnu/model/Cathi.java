package com.lecongdung.testvnu.model;

public class Cathi {
    private int id;
    private String makythi;
    private String cathi;
    private String giothi;
    private String ngaythi;

    public Cathi() {
    }

    public Cathi(int id, String makythi, String cathi, String giothi, String ngaythi) {
        this.id = id;
        this.makythi = makythi;
        this.cathi = cathi;
        this.giothi = giothi;
        this.ngaythi = ngaythi;
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

    public String getCathi() {
        return cathi;
    }

    public void setCathi(String cathi) {
        this.cathi = cathi;
    }

    public String getGiothi() {
        return giothi;
    }

    public void setGiothi(String giothi) {
        this.giothi = giothi;
    }

    public String getNgaythi() {
        return ngaythi;
    }

    public void setNgaythi(String ngaythi) {
        this.ngaythi = ngaythi;
    }
}
