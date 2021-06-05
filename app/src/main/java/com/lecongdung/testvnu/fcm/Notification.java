package com.lecongdung.testvnu.fcm;

public class Notification {
    private String title;
    private String body;
    private String icon;
    private String image;

    public Notification(String title, String body, String icon, String image) {
        this.title = title;
        this.body = body;
        this.icon = icon;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
