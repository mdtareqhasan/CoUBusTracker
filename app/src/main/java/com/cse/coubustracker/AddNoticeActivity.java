package com.cse.coubustracker;


import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowInsetsController;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class AddNoticeActivity extends AppCompatActivity {

    private EditText titleInput, bodyInput, expiryInput;
    private Button postNoticeBtn;
    private LinearLayout noticeListLayout;

    private DatabaseReference noticesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Add Notice");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Status bar appearance based on light/dark mode
        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (currentNightMode == Configuration.UI_MODE_NIGHT_NO) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                getWindow().getInsetsController().setSystemBarsAppearance(
                        WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                        WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                );
            } else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
            getWindow().setStatusBarColor(getResources().getColor(android.R.color.white));
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                getWindow().getInsetsController().setSystemBarsAppearance(0, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS);
            } else {
                getWindow().getDecorView().setSystemUiVisibility(0);
            }
            getWindow().setStatusBarColor(getResources().getColor(android.R.color.black));
        }

        titleInput = findViewById(R.id.noticeTitle);
        bodyInput = findViewById(R.id.noticeBody);
        expiryInput = findViewById(R.id.noticeExpiry);
        postNoticeBtn = findViewById(R.id.postNoticeBtn);
        noticeListLayout = findViewById(R.id.noticeListLayout);

        noticesRef = FirebaseDatabase.getInstance().getReference("notices");

        postNoticeBtn.setOnClickListener(v -> {
            String title = titleInput.getText().toString().trim();
            String body = bodyInput.getText().toString().trim();
            String expiryStr = expiryInput.getText().toString().trim();

            if (TextUtils.isEmpty(title) || TextUtils.isEmpty(body) || TextUtils.isEmpty(expiryStr)) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int expiryHours;
            try {
                expiryHours = Integer.parseInt(expiryStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid expiry time", Toast.LENGTH_SHORT).show();
                return;
            }

            long currentTime = System.currentTimeMillis();
            long expiryTime = currentTime + expiryHours * 3600 * 1000;
            String id = UUID.randomUUID().toString();

            Notice notice = new Notice(id, title, body, currentTime, expiryTime);

            noticesRef.child(id).setValue(notice)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(this, "Notice posted", Toast.LENGTH_SHORT).show();
                        clearInputs();
                        loadNotices();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Failed to post notice: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    });
        });

        loadNotices();
    }

    private void clearInputs() {
        titleInput.setText("");
        bodyInput.setText("");
        expiryInput.setText("");
    }

    private void loadNotices() {
        noticeListLayout.removeAllViews();

        noticesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long now = System.currentTimeMillis();
                for (DataSnapshot noticeSnap : snapshot.getChildren()) {
                    Notice notice = noticeSnap.getValue(Notice.class);
                    if (notice != null && notice.getExpiryTime() > now) {
                        addNoticeView(notice);
                    } else if (notice != null) {
                        noticesRef.child(notice.getId()).removeValue();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AddNoticeActivity.this, "Failed to load notices: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addNoticeView(Notice notice) {
        View noticeView = getLayoutInflater().inflate(R.layout.notice_item, null);

        TextView titleText = noticeView.findViewById(R.id.noticeItemTitle);
        TextView bodyText = noticeView.findViewById(R.id.noticeItemBody);
        TextView timeText = noticeView.findViewById(R.id.noticeItemTime);
        Button deleteBtn = noticeView.findViewById(R.id.noticeItemDelete);

        titleText.setText(notice.getTitle());
        bodyText.setText(notice.getBody());

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault());
        String formattedTime = sdf.format(new Date(notice.getTimestamp()));
        timeText.setText("Posted: " + formattedTime);

        deleteBtn.setOnClickListener(v -> {
            noticesRef.child(notice.getId()).removeValue()
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(this, "Notice deleted", Toast.LENGTH_SHORT).show();
                        loadNotices();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Failed to delete: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });

        noticeListLayout.addView(noticeView);
    }



    // 🔄 Firebase model class
    public static class Notice {
        private String id, title, body;
        private long timestamp, expiryTime;

        public Notice() {}

        public Notice(String id, String title, String body, long timestamp, long expiryTime) {
            this.id = id;
            this.title = title;
            this.body = body;
            this.timestamp = timestamp;
            this.expiryTime = expiryTime;
        }

        public String getId() { return id; }
        public String getTitle() { return title; }
        public String getBody() { return body; }
        public long getTimestamp() { return timestamp; }
        public long getExpiryTime() { return expiryTime; }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
