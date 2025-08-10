package com.cse.coubustracker;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cse.coubustracker.model.Notice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {

    private List<Notice> noticeList;

    public NoticeAdapter(List<Notice> noticeList) {
        this.noticeList = noticeList;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_item2, parent, false);
        return new NoticeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {
        Notice notice = noticeList.get(position);
        holder.title.setText(notice.getTitle());
        holder.body.setText(notice.getBody());
        holder.timestamp.setText("🕒 " + getFormattedTime(notice.getTimestamp()));
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    private String getFormattedTime(long timeMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault());
        return sdf.format(new Date(timeMillis));
    }

    static class NoticeViewHolder extends RecyclerView.ViewHolder {
        TextView title, body, timestamp;

        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.noticeItemTitle);
            body = itemView.findViewById(R.id.noticeItemBody);
            timestamp = itemView.findViewById(R.id.noticeItemTime);
        }
    }
}
