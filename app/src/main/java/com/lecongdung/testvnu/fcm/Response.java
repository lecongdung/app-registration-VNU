package com.lecongdung.testvnu.fcm;

public class Response {
    private String success;

    public Response(){}
    public Response(String success) {
        this.success = success;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
