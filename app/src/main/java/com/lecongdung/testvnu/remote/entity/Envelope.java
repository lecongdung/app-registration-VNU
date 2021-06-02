package com.lecongdung.testvnu.remote.entity;

import java.util.List;

public class Envelope {
    private String from;
    private List<String> to;

    public Envelope() {
    }

    public Envelope(String from, List<String> to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }
}
