package com.lecongdung.testvnu.remote.entity;

import java.util.List;

public class Message {
    private List<String> accepted;
    private List<String> rejected;
    private int envelopeTime;
    private int messageTime;
    private int messageSize;
    private String response;
    private Envelope envelope;
    private String messageId;


    public Message() {
    }

    public Message(List<String> accepted, List<String> rejected, int envelopeTime, int messageTime, int messageSize, String response, Envelope envelope, String messageId) {
        this.accepted = accepted;
        this.rejected = rejected;
        this.envelopeTime = envelopeTime;
        this.messageTime = messageTime;
        this.messageSize = messageSize;
        this.response = response;
        this.envelope = envelope;
        this.messageId = messageId;
    }

    public List<String> getAccepted() {
        return accepted;
    }

    public void setAccepted(List<String> accepted) {
        this.accepted = accepted;
    }

    public List<String> getRejected() {
        return rejected;
    }

    public void setRejected(List<String> rejected) {
        this.rejected = rejected;
    }

    public int getEnvelopeTime() {
        return envelopeTime;
    }

    public void setEnvelopeTime(int envelopeTime) {
        this.envelopeTime = envelopeTime;
    }

    public int getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(int messageTime) {
        this.messageTime = messageTime;
    }

    public int getMessageSize() {
        return messageSize;
    }

    public void setMessageSize(int messageSize) {
        this.messageSize = messageSize;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Envelope getEnvelope() {
        return envelope;
    }

    public void setEnvelope(Envelope envelope) {
        this.envelope = envelope;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
