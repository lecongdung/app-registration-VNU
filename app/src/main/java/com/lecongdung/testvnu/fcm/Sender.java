package com.lecongdung.testvnu.fcm;

public class Sender {
    public static final String HIGH_PRIORITY = "high";
    public static final String NORMAL_PRIORITY = "normal";
    private String to;
    private Notification notification;
    private Data data;
    private String priority;
    public Sender() {
    }

    public Sender(String to, Notification notification, Data data, String priority) {
        this.to = to;
        this.notification = notification;
        this.data = data;
        this.priority = priority;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
