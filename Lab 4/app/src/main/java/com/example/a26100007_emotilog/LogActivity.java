package com.example.a26100007_emotilog;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// Shows all logged emotions in a scrollable list, newest first
public class LogActivity extends AppCompatActivity {

    private LogAdapter adapter;
    private List<EmoticonEntry> entries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Emotion Log");
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerViewLog);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LogAdapter(entries);
        recyclerView.setAdapter(adapter);
    }

    // reload entries every time we come back (picks up new logs)
    @Override
    protected void onResume() {
        super.onResume();
        loadEntries();
    }

    // add "Clear All" option to the toolbar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Clear All");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        if (item.getItemId() == 1) {
            confirmClearAll();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // ask for confirmation before deleting everything
    private void confirmClearAll() {
        new AlertDialog.Builder(this)
                .setTitle("Clear All Logs")
                .setMessage("Delete all emotion logs? This cannot be undone.")
                .setPositiveButton("Clear", (dialog, which) -> {
                    EmoticonDao dao = EmoticonDatabase.getDatabase(this).emoticonDao();
                    new Thread(() -> {
                        dao.deleteAll();
                        runOnUiThread(() -> {
                            entries.clear();
                            adapter.notifyDataSetChanged();
                        });
                    }).start();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void loadEntries() {
        EmoticonDao dao = EmoticonDatabase.getDatabase(this).emoticonDao();

        new Thread(() -> {
            List<EmoticonEntry> result = dao.getAllEntries();
            runOnUiThread(() -> {
                entries.clear();
                entries.addAll(result);
                adapter.notifyDataSetChanged();
            });
        }).start();
    }
}
