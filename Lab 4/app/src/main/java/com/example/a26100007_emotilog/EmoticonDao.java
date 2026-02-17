package com.example.a26100007_emotilog;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

// DAO for all database operations on emotion_log table
@Dao
public interface EmoticonDao {

    @Insert
    void insert(EmoticonEntry entry);

    // all entries, newest first
    @Query("SELECT * FROM emotion_log ORDER BY timestamp DESC")
    List<EmoticonEntry> getAllEntries();

    // entries within a time range (used for daily filtering)
    @Query("SELECT * FROM emotion_log WHERE timestamp >= :startMs AND timestamp < :endMs ORDER BY timestamp DESC")
    List<EmoticonEntry> getEntriesForDay(long startMs, long endMs);

    // count per emotion across all time
    @Query("SELECT emotion, COUNT(*) AS count FROM emotion_log GROUP BY emotion ORDER BY count DESC")
    List<EmotionCount> getEmotionCounts();

    // count per emotion for a specific day
    @Query("SELECT emotion, COUNT(*) AS count FROM emotion_log WHERE timestamp >= :startMs AND timestamp < :endMs GROUP BY emotion ORDER BY count DESC")
    List<EmotionCount> getEmotionCountsForDay(long startMs, long endMs);

    // wipe all logs
    @Query("DELETE FROM emotion_log")
    void deleteAll();
}
