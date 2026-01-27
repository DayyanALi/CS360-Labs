package com.example.lonelytwitter;

import java.util.Date;

public class Mood {
    public Date date;

    // Default constructor (sets current date)
    public Mood() {
        this.date = new Date();
    }

    // Constructor with date
    public Mood(Date date) {
        this.date = date;
    }

    // Getter
    public Date getDate() {
        return date;
    }

    // Setter
    public void setDate(Date date) {
        this.date = date;
    }

    // Abstract method (must be overridden)
    public abstract String getMoodType();
}
