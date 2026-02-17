package com.example.a26100007_emotilog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// Main screen — shows 9 emotion buttons and navigation to logs/summary
public class MainActivity extends AppCompatActivity {

    private EmoticonDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dao = EmoticonDatabase.getDatabase(this).emoticonDao();
    }

    // called by each emotion button via android:onClick in XML
    public void onEmotionClick(View view) {
        String emotion = view.getTag().toString();

        // insert on background thread so we don't block the UI
        new Thread(() -> {
            dao.insert(new EmoticonEntry(emotion, System.currentTimeMillis()));
        }).start();

        String emoji = EmoticonUtils.getEmoji(emotion);
        Toast.makeText(this, emoji + " " + emotion + " logged!", Toast.LENGTH_SHORT).show();
    }

    public void openLogs(View view) {
        startActivity(new Intent(this, LogActivity.class));
    }

    public void openSummary(View view) {
        startActivity(new Intent(this, SummaryActivity.class));
    }
}
