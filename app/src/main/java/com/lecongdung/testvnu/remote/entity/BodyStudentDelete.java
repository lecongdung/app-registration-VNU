package com.lecongdung.testvnu.remote.entity;

public class BodyStudentDelete {

    private String tendangnhap;


    public BodyStudentDelete() {
    }

    public BodyStudentDelete(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }
}
