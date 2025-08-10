package com.cse.coubustracker;


import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsetsController;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coubustracker.model.Notice;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoticeListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoticeAdapter adapter;
    private List<Notice> noticeList;
    private DatabaseReference noticeRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_list);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Notice List");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


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

        recyclerView = findViewById(R.id.noticeRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        noticeList = new ArrayList<>();
        adapter = new NoticeAdapter(noticeList);
        recyclerView.setAdapter(adapter);

        noticeRef = FirebaseDatabase.getInstance().getReference("notices");

        loadNotices();
    }

    private void loadNotices() {
        noticeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                noticeList.clear();
                long now = System.currentTimeMillis();

                for (DataSnapshot snap : snapshot.getChildren()) {
                    Notice notice = snap.getValue(Notice.class);
                    if (notice != null) {
                        if (notice.getExpiryTime() > now) {
                            noticeList.add(notice);
                        } else {
                            // Remove expired notice
                            noticeRef.child(notice.getId()).removeValue();
                        }
                    }
                }

                // Sort by timestamp descending (latest first)
                Collections.sort(noticeList, (a, b) -> Long.compare(b.getTimestamp(), a.getTimestamp()));

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(NoticeListActivity.this, "Failed to load notices", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Go back to previous activity
        return true;
    }
}
