package com.example.a26100007_emotilog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

// Daily summary screen — shows emotion counts and frequencies for a selected date
public class SummaryActivity extends AppCompatActivity {

    private Calendar selectedDate = Calendar.getInstance();
    private SummaryAdapter adapter;
    private List<EmotionCount> summaryList = new ArrayList<>();
    private TextView textDate, textTotal;

    private final SimpleDateFormat dateFormat =
            new SimpleDateFormat("EEEE, MMM dd, yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Daily Summary");
        }

        textDate = findViewById(R.id.textDate);
        textTotal = findViewById(R.id.textTotal);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewSummary);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SummaryAdapter(summaryList);
        recyclerView.setAdapter(adapter);

        // tap date text to open a date picker
        textDate.setOnClickListener(v -> showDatePicker());

        // prev/next day buttons
        findViewById(R.id.btnPrevDay).setOnClickListener(v -> {
            selectedDate.add(Calendar.DAY_OF_MONTH, -1);
            loadSummary();
        });
        findViewById(R.id.btnNextDay).setOnClickListener(v -> {
            selectedDate.add(Calendar.DAY_OF_MONTH, 1);
            loadSummary();
        });

        loadSummary(); // default to today
    }

    private void showDatePicker() {
        new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    selectedDate.set(year, month, dayOfMonth);
                    loadSummary();
                },
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    // query DB for emotion counts within the selected day (midnight to midnight)
    private void loadSummary() {
        textDate.setText(dateFormat.format(selectedDate.getTime()));

        Calendar dayStart = (Calendar) selectedDate.clone();
        dayStart.set(Calendar.HOUR_OF_DAY, 0);
        dayStart.set(Calendar.MINUTE, 0);
        dayStart.set(Calendar.SECOND, 0);
        dayStart.set(Calendar.MILLISECOND, 0);

        Calendar dayEnd = (Calendar) dayStart.clone();
        dayEnd.add(Calendar.DAY_OF_MONTH, 1);

        long startMs = dayStart.getTimeInMillis();
        long endMs = dayEnd.getTimeInMillis();

        EmoticonDao dao = EmoticonDatabase.getDatabase(this).emoticonDao();

        new Thread(() -> {
            List<EmotionCount> result = dao.getEmotionCountsForDay(startMs, endMs);
            int totalCount = 0;
            for (EmotionCount ec : result) totalCount += ec.count;
            final int total = totalCount;

            runOnUiThread(() -> {
                textTotal.setText(total == 0
                        ? "No emotions logged on this day"
                        : "Total: " + total + " emotion" + (total != 1 ? "s" : "") + " logged");

                summaryList.clear();
                summaryList.addAll(result);
                adapter.setTotalCount(total);
                adapter.notifyDataSetChanged();
            });
        }).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
