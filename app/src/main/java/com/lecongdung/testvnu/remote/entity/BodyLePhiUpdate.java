package com.lecongdung.testvnu.remote.entity;

public class BodyLePhiUpdate {

    private String Makythi;
    private int Lephidangky;
    private int Lephidanop;
    private String Nguoithu;
    private String Ngaythu;
    private String status;
    private String Ngaydangky;

    public BodyLePhiUpdate() {
    }

    public BodyLePhiUpdate(String makythi, int lephidangky, int lephidanop, String nguoithu, String ngaythu, String status, String ngaydangky) {
        Makythi = makythi;
        Lephidangky = lephidangky;
        Lephidanop = lephidanop;
        Nguoithu = nguoithu;
        Ngaythu = ngaythu;
        this.status = status;
        Ngaydangky = ngaydangky;
    }

    public String getMakythi() {
        return Makythi;
    }

    public void setMakythi(String makythi) {
        Makythi = makythi;
    }

    public int getLephidangky() {
        return Lephidangky;
    }

    public void setLephidangky(int lephidangky) {
        Lephidangky = lephidangky;
    }

    public int getLephidanop() {
        return Lephidanop;
    }

    public void setLephidanop(int lephidanop) {
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
