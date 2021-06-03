package com.lecongdung.testvnu.model;

public class Cathi {
    private int Id;
    private String Makythi;
    private int Cathi;
    private String Giothi;
    private String Ngaythi;
    private String createdAt;
    private String updatedAt;

    public Cathi() {
    }

    public Cathi(int id, String makythi, int cathi, String giothi, String ngaythi, String createdAt, String updatedAt) {
        Id = id;
        Makythi = makythi;
        Cathi = cathi;
        Giothi = giothi;
        Ngaythi = ngaythi;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getMakythi() {
        return Makythi;
    }

    public void setMakythi(String makythi) {
        Makythi = makythi;
    }

    public int getCathi() {
        return Cathi;
    }

    public void setCathi(int cathi) {
        Cathi = cathi;
    }

    public String getGiothi() {
        return Giothi;
    }

    public void setGiothi(String giothi) {
        Giothi = giothi;
    }

    public String getNgaythi() {
        return Ngaythi;
    }

    public void setNgaythi(String ngaythi) {
        Ngaythi = ngaythi;
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
