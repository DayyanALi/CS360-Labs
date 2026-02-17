package com.example.a26100007_emotilog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

// Adapter for the summary list — shows each emotion's count and frequency %
public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.SummaryViewHolder> {

    private final List<EmotionCount> summaryList;
    private int totalCount = 0;

    public SummaryAdapter(List<EmotionCount> summaryList) {
        this.summaryList = summaryList;
    }

    // set by the activity after computing the total for the day
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @NonNull
    @Override
    public SummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_summary_entry, parent, false);
        return new SummaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SummaryViewHolder holder, int position) {
        EmotionCount item = summaryList.get(position);

        holder.textEmoji.setText(EmoticonUtils.getEmoji(item.emotion));
        holder.textEmotion.setText(item.emotion);
        holder.textCount.setText(String.valueOf(item.count));

        float pct = totalCount > 0 ? (item.count * 100f / totalCount) : 0;
        holder.textFrequency.setText(String.format(Locale.getDefault(), "%.1f%%", pct));
        holder.progressBar.setProgress(Math.round(pct));
    }

    @Override
    public int getItemCount() {
        return summaryList.size();
    }

    static class SummaryViewHolder extends RecyclerView.ViewHolder {
        TextView textEmoji, textEmotion, textCount, textFrequency;
        ProgressBar progressBar;

        SummaryViewHolder(@NonNull View itemView) {
            super(itemView);
            textEmoji = itemView.findViewById(R.id.textSummaryEmoji);
            textEmotion = itemView.findViewById(R.id.textSummaryEmotion);
            textCount = itemView.findViewById(R.id.textSummaryCount);
            textFrequency = itemView.findViewById(R.id.textSummaryFrequency);
            progressBar = itemView.findViewById(R.id.progressBarFrequency);
        }
    }
}
