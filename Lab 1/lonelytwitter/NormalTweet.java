package com.example.lonelytwitter;

import java.util.Date;

public class NormalTweet extends Tweet{
    public NormalTweet(String message) {
        super(message);
    }

    public NormalTweet(Date data, String message) {
        super(data, message);
    }

    @Override
    public Boolean isImportant() {
        return Boolean.FALSE;
    }
}
