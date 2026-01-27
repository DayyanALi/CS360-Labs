package com.example.lonelytwitter;

import java.util.Date;

public abstract class Tweet implements Tweetable{
    private Date data;

    private String message;

    public Tweet(String messasge) {
        this.message = messasge;
    }

    public Tweet(Date data, String message) {
        this.data = data;
        this.message = message;
    }

    public Date getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public abstract Boolean isImportant();
}
