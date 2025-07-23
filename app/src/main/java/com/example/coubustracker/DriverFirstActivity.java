package com.example.coubustracker;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsetsController;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DriverFirstActivity extends AppCompatActivity {
    AllBusesInfo allBusesInfo = new AllBusesInfo();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_first);

        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        if (currentNightMode == Configuration.UI_MODE_NIGHT_NO) {
            // Light mode: set dark icons on white background
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
            // Dark mode: set light icons on dark background
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                getWindow().getInsetsController().setSystemBarsAppearance(
                        0,
                        WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                );
            } else {
                getWindow().getDecorView().setSystemUiVisibility(0); // default light icons
            }
            getWindow().setStatusBarColor(getResources().getColor(android.R.color.black));
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Driver Bus List");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


    }

    public void openLogin(View view) {
        String role = view.getTag().toString();
        Intent intent;

        switch (role) {
            case "red2":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.rb2_title);
                break;
            case "red3":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.rb3_title);
                break;
            case "red4":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.rb4_title);
                break;
            case "red5":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.rb5_title);
                break;
            case "red6":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.rb6_title);
                break;
            case "red7":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.rb7_title);
                break;
            case "red8":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.rb8_title);
                break;
            case "red10":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.rb10_title);
                break;
            case "blue3":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.bb03_title);
                break;
            case "blue4":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.bb04_title);
                break;
            case "blue5":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.bb05_title);
                break;
            case "blue11":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.bb11_title);
                break;
            case "blue25":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.bb25_title);
                break;
            case "blue31":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.bb31_title);
                break;
            case "blue32":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.bb32_title);
                break;
            case "blue33":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.bb33_title);
                break;
            case "eve1":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.eb1_title);
                break;
            case "eve2":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.eb2_title);
                break;
            case "eve3":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.eb3_title);
                break;
            case "eve4":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.eb4_title);
                break;
            case "eve5":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.eb5_title);
                break;
            case "eve6":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.eb6_title);
                break;
            case "eve7":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.eb7_title);
                break;
            case "teacher1":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.teacherBus1_title);
                break;
            case "teacher2":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.teacherBus2_title);
                break;
            case "officer1":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.officerBus1_title);
                break;
            case "staff1":
                intent = new Intent(DriverFirstActivity.this, DriverDetails.class);
                intent.putExtra("busName", allBusesInfo.sb1_title);
                break;

            case "busscedule":
                intent = new Intent(DriverFirstActivity.this, busscedule.class);
                break;


            default:
                return;  // Handle default or error
        }

        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Go back to previous activity
        return true;
    }
}
