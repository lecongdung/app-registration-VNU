package com.lecongdung.testvnu.remote.entity;

public class BodyStudentUpdatePassword {

    private String password;


    public BodyStudentUpdatePassword() {
    }

    public BodyStudentUpdatePassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
