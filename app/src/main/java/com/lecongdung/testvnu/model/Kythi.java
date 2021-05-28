package com.lecongdung.testvnu.model;

public class Kythi {
    private int id;
    private String makythi;
    private String tenkythi;
    private String mota;
    private String tungay;
    private String toingay;
    private int socathi;
    private String handaky;
    private int trangthai;
    private int taocathi;
    private String anhkythi;


    public Kythi() {
    }

    public Kythi(int id, String makythi, String tenkythi, String mota, String tungay, String toingay, int socathi, String handaky, int trangthai, int taocathi, String anhkythi) {
        this.id = id;
        this.makythi = makythi;
        this.tenkythi = tenkythi;
        this.mota = mota;
        this.tungay = tungay;
        this.toingay = toingay;
        this.socathi = socathi;
        this.handaky = handaky;
        this.trangthai = trangthai;
        this.taocathi = taocathi;
        this.anhkythi = anhkythi;
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

    public String getTenkythi() {
        return tenkythi;
    }

    public void setTenkythi(String tenkythi) {
        this.tenkythi = tenkythi;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getTungay() {
        return tungay;
    }

    public void setTungay(String tungay) {
        this.tungay = tungay;
    }

    public String getToingay() {
        return toingay;
    }

    public void setToingay(String toingay) {
        this.toingay = toingay;
    }

    public int getSocathi() {
        return socathi;
    }

    public void setSocathi(int socathi) {
        this.socathi = socathi;
    }

    public String getHandaky() {
        return handaky;
    }

    public void setHandaky(String handaky) {
        this.handaky = handaky;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public int getTaocathi() {
        return taocathi;
    }

    public void setTaocathi(int taocathi) {
        this.taocathi = taocathi;
    }

    public String getAnhkythi() {
        return anhkythi;
    }

    public void setAnhkythi(String anhkythi) {
        this.anhkythi = anhkythi;
    }
}
