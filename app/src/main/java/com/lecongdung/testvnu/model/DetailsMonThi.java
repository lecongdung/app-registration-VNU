package com.lecongdung.testvnu.model;

public class DetailsMonThi {
    private String maMonthi;
    private String ngaythi;
    private int cathi;
    private String giothi;
    private String diemthi;

    public DetailsMonThi() {
    }

    public DetailsMonThi(String maMonthi, String ngaythi, int cathi, String giothi, String diemthi) {
        this.maMonthi = maMonthi;
        this.ngaythi = ngaythi;
        this.cathi = cathi;
        this.giothi = giothi;
        this.diemthi = diemthi;
    }

    public String getMaMonthi() {
        return maMonthi;
    }

    public void setMaMonthi(String maMonthi) {
        this.maMonthi = maMonthi;
    }

    public String getNgaythi() {
        return ngaythi;
    }

    public void setNgaythi(String ngaythi) {
        this.ngaythi = ngaythi;
    }

    public int getCathi() {
        return cathi;
    }

    public void setCathi(int cathi) {
        this.cathi = cathi;
    }

    public String getGiothi() {
        return giothi;
    }

    public void setGiothi(String giothi) {
        this.giothi = giothi;
    }

    public String getDiemthi() {
        return diemthi;
    }

    public void setDiemthi(String diemthi) {
        this.diemthi = diemthi;
    }
}
