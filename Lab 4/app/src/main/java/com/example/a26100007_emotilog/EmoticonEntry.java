package com.example.a26100007_emotilog;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Room entity for a single emotion log entry (stored in "emotion_log" table)
@Entity(tableName = "emotion_log")
public class EmoticonEntry {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String emotion;   // e.g. "Happy", "Sad"
    public long timestamp;   // when the emotion was logged (millis)

    public EmoticonEntry(String emotion, long timestamp) {
        this.emotion = emotion;
        this.timestamp = timestamp;
    }
}
