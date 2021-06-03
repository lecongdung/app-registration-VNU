package com.lecongdung.testvnu.remote.entity;

public class BodyStudentUpdateDetail {

    private String Hoten;
    private String Sodienthoai;
    private String Ngaysinh;
    private String Gioitinh;
    private String Noisinh;
    private String Dantoc;
    private String SoCMND;
    private String Ngaycap;
    private String Noicap;
    private String Khuvuc;


    public BodyStudentUpdateDetail() {
    }

    public BodyStudentUpdateDetail(String hoten, String sodienthoai, String ngaysinh, String gioitinh, String noisinh, String dantoc, String soCMND, String ngaycap, String noicap, String khuvuc) {
        Hoten = hoten;
        Sodienthoai = sodienthoai;
        Ngaysinh = ngaysinh;
        Gioitinh = gioitinh;
        Noisinh = noisinh;
        Dantoc = dantoc;
        SoCMND = soCMND;
        Ngaycap = ngaycap;
        Noicap = noicap;
        Khuvuc = khuvuc;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String hoten) {
        Hoten = hoten;
    }

    public String getSodienthoai() {
        return Sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        Sodienthoai = sodienthoai;
    }

    public String getNgaysinh() {
        return Ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        Ngaysinh = ngaysinh;
    }

    public String getGioitinh() {
        return Gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        Gioitinh = gioitinh;
    }

    public String getNoisinh() {
        return Noisinh;
    }

    public void setNoisinh(String noisinh) {
        Noisinh = noisinh;
    }

    public String getDantoc() {
        return Dantoc;
    }

    public void setDantoc(String dantoc) {
        Dantoc = dantoc;
    }

    public String getSoCMND() {
        return SoCMND;
    }

    public void setSoCMND(String soCMND) {
        SoCMND = soCMND;
    }

    public String getNgaycap() {
        return Ngaycap;
    }

    public void setNgaycap(String ngaycap) {
        Ngaycap = ngaycap;
    }

    public String getNoicap() {
        return Noicap;
    }

    public void setNoicap(String noicap) {
        Noicap = noicap;
    }

    public String getKhuvuc() {
        return Khuvuc;
    }

    public void setKhuvuc(String khuvuc) {
        Khuvuc = khuvuc;
    }
}
