package com.lecongdung.testvnu.model;

public class Monthi {
    private int Id;
    private String Makythi;
    private String Madiemthi;
    private String Mamonthi;
    private int Cathi;
    private int checked;
    private String createdAt;
    private String updatedAt;

    public Monthi() {
    }

    public Monthi(int id, String makythi, String madiemthi, String mamonthi, int cathi, int checked, String createdAt, String updatedAt) {
        Id = id;
        Makythi = makythi;
        Madiemthi = madiemthi;
        Mamonthi = mamonthi;
        Cathi = cathi;
        this.checked = checked;
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

    public String getMadiemthi() {
        return Madiemthi;
    }

    public void setMadiemthi(String madiemthi) {
        Madiemthi = madiemthi;
    }

    public String getMamonthi() {
        return Mamonthi;
    }

    public void setMamonthi(String mamonthi) {
        Mamonthi = mamonthi;
    }

    public int getCathi() {
        return Cathi;
    }

    public void setCathi(int cathi) {
        Cathi = cathi;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
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
