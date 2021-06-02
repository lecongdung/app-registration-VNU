package com.lecongdung.testvnu.remote.entity;

public class ResponeSendOTP {

    private Message message;
    private String result;


    public ResponeSendOTP() {
    }

    public ResponeSendOTP(Message message, String result) {
        this.message = message;
        this.result = result;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
