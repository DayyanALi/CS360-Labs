package com.example.a26100007_emotilog;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// Room database — singleton so we don't open multiple connections
@Database(entities = {EmoticonEntry.class}, version = 1, exportSchema = false)
public abstract class EmoticonDatabase extends RoomDatabase {

    public abstract EmoticonDao emoticonDao();

    private static volatile EmoticonDatabase INSTANCE;

    public static EmoticonDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (EmoticonDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            EmoticonDatabase.class,
                            "emotilog_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
