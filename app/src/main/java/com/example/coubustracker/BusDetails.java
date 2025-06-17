package com.example.coubustracker;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsetsController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coubustracker.model.BusLocation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BusDetails extends AppCompatActivity {

    private TextView tvDriverName, tvMobileNumber, tvLocationLink;
    private String locationLink = "", busName, busImage, busTimeRoute;
    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_details);

        busName=getIntent().getStringExtra("busName");
        busImage=getIntent().getStringExtra("busImage");
        busTimeRoute=getIntent().getStringExtra("busTimeRoute");

        tvDriverName = findViewById(R.id.tvDriverName);
        tvMobileNumber = findViewById(R.id.tvMobileNumber);
        tvLocationLink = findViewById(R.id.tvLocationLink);
        textView = findViewById(R.id.tvBusTimeRoute);
        imageView = findViewById(R.id.imgBus);
        tvLocationLink.setTextSize(16);

        textView.setText(busTimeRoute);

        Resources resources = getResources();
        final int resourceId = resources.getIdentifier(busImage,
                "drawable", getPackageName());

        imageView.setImageResource(resourceId);

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
            getSupportActionBar().setTitle(busName);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }



        FirebaseDatabase.getInstance().getReference("buses")
                .child(busName)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        BusLocation loc = snapshot.getValue(BusLocation.class);
                        if (loc != null) {
                            tvDriverName.setText("Driver Name: " + loc.getDriverName());
                            tvMobileNumber.setText("Mobile Number: " + loc.getMobileNumber());
                            tvLocationLink.setText("OPEN LIVE LOCATION");
                            locationLink = loc.getLocationLink();
                        } else {
                            Toast.makeText(BusDetails.this, busName + " not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Toast.makeText(BusDetails.this, busName + " not found", Toast.LENGTH_SHORT).show();

                    }
                });

        tvLocationLink.setOnClickListener(v -> {
            if (locationLink == null || locationLink.isEmpty()) {
                Toast.makeText(this, "Location link not available", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(BusDetails.this, WebViewActivity.class);
            intent.putExtra("busName", busName);
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