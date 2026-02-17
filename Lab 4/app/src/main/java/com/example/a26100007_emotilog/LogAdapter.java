package com.example.a26100007_emotilog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

// Adapter for the log list — binds each EmoticonEntry to a card row
public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder> {

    private final List<EmoticonEntry> entries;
    private final SimpleDateFormat dateFormat =
            new SimpleDateFormat("MMM dd, yyyy  hh:mm:ss a", Locale.getDefault());

    public LogAdapter(List<EmoticonEntry> entries) {
        this.entries = entries;
    }

    @NonNull
    @Override
    public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_log_entry, parent, false);
        return new LogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
        EmoticonEntry entry = entries.get(position);
        holder.textEmoji.setText(EmoticonUtils.getEmoji(entry.emotion));
        holder.textEmotion.setText(entry.emotion);
        holder.textTimestamp.setText(dateFormat.format(new Date(entry.timestamp)));
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    static class LogViewHolder extends RecyclerView.ViewHolder {
        TextView textEmoji, textEmotion, textTimestamp;

        LogViewHolder(@NonNull View itemView) {
            super(itemView);
            textEmoji = itemView.findViewById(R.id.textEmoji);
            textEmotion = itemView.findViewById(R.id.textEmotion);
            textTimestamp = itemView.findViewById(R.id.textTimestamp);
        }
    }
}
