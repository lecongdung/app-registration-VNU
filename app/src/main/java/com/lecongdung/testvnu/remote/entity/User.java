package com.lecongdung.testvnu.remote.entity;

public class User {
    private int Id;
    private String Email;

    public User() {
    }

    public User(int id, String email) {
        Id = id;
        Email = email;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
