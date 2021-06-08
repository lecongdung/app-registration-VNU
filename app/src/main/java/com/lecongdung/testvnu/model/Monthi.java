package com.lecongdung.testvnu.model;

public class Monthi {
    private int Id;
    private String Makythi;
    private String MaMonthi;
    private String Giothi;
    private String Ngaythi;
    private String Diadiemthi;
    private int Lephithi;
    private int Thoigianlambai;
    private int Luachon;
    private String createdAt;
    private String updatedAt;

    public Monthi() {
    }


    public Monthi(int id, String makythi, String maMonthi, String giothi, String ngaythi, String diadiemthi, int lephithi, int thoigianlambai, int luachon, String createdAt, String updatedAt) {
        Id = id;
        Makythi = makythi;
        MaMonthi = maMonthi;
        Giothi = giothi;
        Ngaythi = ngaythi;
        Diadiemthi = diadiemthi;
        Lephithi = lephithi;
        Thoigianlambai = thoigianlambai;
        Luachon = luachon;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        if (id != 0) {
            Id = id;
        }
    }

    public String getMakythi() {
        return Makythi;
    }

    public void setMakythi(String makythi) {
        if (makythi != null) {
            Makythi = makythi;
        }
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        if (createdAt != null) {
            this.createdAt = createdAt;
        }
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        if (updatedAt != null) {
            this.updatedAt = updatedAt;
        }
    }

    public void setMaMonthi(String maMonthi) {
        if(maMonthi != null)MaMonthi = maMonthi;
    }

    public void setGiothi(String giothi) {
        if(giothi != null)Giothi = giothi;
    }

    public void setNgaythi(String ngaythi) {
        if(ngaythi != null)Ngaythi = ngaythi;
    }

    public void setDiadiemthi(String diadiemthi) {
        if(diadiemthi != null) Diadiemthi = diadiemthi;
    }

    public void setLephithi(int lephithi) {
         Lephithi = lephithi;
    }

    public void setThoigianlambai(int thoigianlambai) {
        Thoigianlambai = thoigianlambai;
    }

    public void setLuachon(int luachon) {
        Luachon = luachon;
    }

    public String getMaMonthi() {
        return MaMonthi;
    }

    public String getGiothi() {
        return Giothi;
    }

    public String getNgaythi() {
        return Ngaythi;
    }

    public String getDiadiemthi() {
        return Diadiemthi;
    }

    public int getLephithi() {
        return Lephithi;
    }

    public int getThoigianlambai() {
        return Thoigianlambai;
    }

    public int getLuachon() {
        return Luachon;
    }
}
