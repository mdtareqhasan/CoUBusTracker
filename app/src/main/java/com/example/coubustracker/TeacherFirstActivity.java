package com.example.coubustracker;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowInsetsController;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class TeacherFirstActivity extends AppCompatActivity {

    AllBusesInfo allBusesInfo = new AllBusesInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_first);


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
                getWindow().getInsetsController().setSystemBarsAppearance(
                        0,
                        WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                );
            } else {
                getWindow().getDecorView().setSystemUiVisibility(0);
            }
            getWindow().setStatusBarColor(getResources().getColor(android.R.color.black));
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Bus List");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void openLogin(View view) {
        Object tagObj = view.getTag();
        if (tagObj == null) return;

        String role = tagObj.toString();
        Intent intent;

        switch (role) {
            case "teacher1":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.teacherBus1_title);
                intent.putExtra("busImage", allBusesInfo.teacherBus1_image);
                intent.putExtra("busTimeRoute", allBusesInfo.teacherBus1_time_route);
                break;
            case "teacher2":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.teacherBus2_title);
                intent.putExtra("busImage", allBusesInfo.teacherBus2_image);
                intent.putExtra("busTimeRoute", allBusesInfo.teacherBus2_time_route);
                break;
            case "officer1":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.officerBus1_title);
                intent.putExtra("busImage", allBusesInfo.officerBus1_image);
                intent.putExtra("busTimeRoute", allBusesInfo.officerBus1_time_route);
                break;
            case "busscedule2":
                intent = new Intent(this, BusScedule2.class);
                break;
            default:
                return;
        }

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.student_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_about_us) {
            startActivity(new Intent(this, AboutUsActivity.class));
            return true;
        } else if (id == R.id.menu_notifications) {
            startActivity(new Intent(this, NoticeListActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
