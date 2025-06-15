package com.example.coubustracker;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsetsController;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coubustracker.model.BusLocation;
import com.google.firebase.database.*;

public class studentbluebus33 extends AppCompatActivity {

    private TextView tvDriverName, tvMobileNumber, tvLocationLink;
    private String locationLink = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentbluebus33);

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

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Blue Bus 33");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tvDriverName = findViewById(R.id.tvDriverName);
        tvMobileNumber = findViewById(R.id.tvMobileNumber);
        tvLocationLink = findViewById(R.id.tvLocationLink);
        tvLocationLink.setTextSize(16);

        FirebaseDatabase.getInstance().getReference("buses")
                .child("Blue Bus 33")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        BusLocation loc = snapshot.getValue(BusLocation.class);
                        if (loc != null) {
                            tvDriverName.setText("Driver Name: " + loc.getDriverName());
                            tvMobileNumber.setText("Mobile Number: " + loc.getMobileNumber());
                            tvLocationLink.setText("Tap here to open live location");
                            locationLink = loc.getLocationLink();
                        } else {
                            Toast.makeText(studentbluebus33.this, "Blue Bus 33 not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Toast.makeText(studentbluebus33.this, "Error loading data", Toast.LENGTH_SHORT).show();
                    }
                });

        tvLocationLink.setOnClickListener(v -> {
            if (locationLink == null || locationLink.isEmpty()) {
                Toast.makeText(this, "Location link not available", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(studentbluebus33.this, WebViewActivity.class);
            intent.putExtra("busName", "Blue Bus 33");
            intent.putExtra("location_url", locationLink);
            startActivity(intent);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
