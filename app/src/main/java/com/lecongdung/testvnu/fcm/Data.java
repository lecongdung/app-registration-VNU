package com.lecongdung.testvnu.fcm;

public class Data {
    private String uid, avatar, title, body;

    public Data() {

    }

    public Data(String uid, String avatar, String title, String body) {
        this.uid = uid;
        this.avatar = avatar;
        this.title = title;
        this.body = body;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
