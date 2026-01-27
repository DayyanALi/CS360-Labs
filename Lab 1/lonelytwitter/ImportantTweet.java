package com.example.lonelytwitter;

import java.util.Date;

public class ImportantTweet extends Tweet{
    public ImportantTweet(String message) {
        super(message);
    }

    public ImportantTweet(Date data, String message) {
        super(data, message);
    }

    @Override
    public Boolean isImportant() {
        return Boolean.TRUE;
    }
}
