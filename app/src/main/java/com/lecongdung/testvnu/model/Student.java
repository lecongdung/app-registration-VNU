package com.lecongdung.testvnu.model;

import android.util.Log;
import android.widget.Toast;

import com.lecongdung.testvnu.view.LoginActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Student {

    private int Id;
    private String tendangnhap;
    private String HoTen;
    private String Sodienthoai;
    private String Email;
    private String Trangthai;
    private String verify;
    private String createdAt;
    private String updatedAt;

    public Student() {
    }

    public Student(int id, String tendangnhap, String hoTen, String sodienthoai, String email, String trangthai, String verify, String createdAt, String updatedAt) {
        Id = id;
        this.tendangnhap = tendangnhap;
        HoTen = hoTen;
        Sodienthoai = sodienthoai;
        Email = email;
        Trangthai = trangthai;
        this.verify = verify;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getSodienthoai() {
        return Sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        Sodienthoai = sodienthoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTrangthai() {
        return Trangthai;
    }

    public void setTrangthai(String trangthai) {
        Trangthai = trangthai;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
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
