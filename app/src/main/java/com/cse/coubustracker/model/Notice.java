package com.cse.coubustracker.model;

public class Notice {
    private String id, title, body;
    private long timestamp, expiryTime;

    public Notice() {} // Required for Firebase

    public Notice(String id, String title, String body, long timestamp, long expiryTime) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.timestamp = timestamp;
        this.expiryTime = expiryTime;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getBody() { return body; }
    public long getTimestamp() { return timestamp; }
    public long getExpiryTime() { return expiryTime; }
}
