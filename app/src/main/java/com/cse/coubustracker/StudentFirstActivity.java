package com.cse.coubustracker;


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

public class StudentFirstActivity extends AppCompatActivity {

    AllBusesInfo allBusesInfo = new AllBusesInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_first);

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
            case "red2":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.rb2_title);
                intent.putExtra("busImage", allBusesInfo.rb2_image);
                intent.putExtra("busTimeRoute", allBusesInfo.rb2_time_route);
                break;
            case "red3":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.rb3_title);
                intent.putExtra("busImage", allBusesInfo.rb3_image);
                intent.putExtra("busTimeRoute", allBusesInfo.rb3_time_route);
                break;
            case "red4":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.rb4_title);
                intent.putExtra("busImage", allBusesInfo.rb4_image);
                intent.putExtra("busTimeRoute", allBusesInfo.rb4_time_route);
                break;
            case "red5":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.rb5_title);
                intent.putExtra("busImage", allBusesInfo.rb5_image);
                intent.putExtra("busTimeRoute", allBusesInfo.rb5_time_route);
                break;
            case "red6":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.rb6_title);
                intent.putExtra("busImage", allBusesInfo.rb6_image);
                intent.putExtra("busTimeRoute", allBusesInfo.rb6_time_route);
                break;
            case "red7":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.rb7_title);
                intent.putExtra("busImage", allBusesInfo.rb7_image);
                intent.putExtra("busTimeRoute", allBusesInfo.rb7_time_route);
                break;
            case "red8":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.rb8_title);
                intent.putExtra("busImage", allBusesInfo.rb8_image);
                intent.putExtra("busTimeRoute", allBusesInfo.rb8_time_route);
                break;
            case "red10":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.rb10_title);
                intent.putExtra("busImage", allBusesInfo.rb10_image);
                intent.putExtra("busTimeRoute", allBusesInfo.rb10_time_route);
                break;
            case "blue3":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.bb03_title);
                intent.putExtra("busImage", allBusesInfo.bb03_image);
                intent.putExtra("busTimeRoute", allBusesInfo.bb03_time_route);
                break;
            case "blue4":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.bb04_title);
                intent.putExtra("busImage", allBusesInfo.bb04_image);
                intent.putExtra("busTimeRoute", allBusesInfo.bb04_time_route);
                break;
            case "blue5":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.bb05_title);
                intent.putExtra("busImage", allBusesInfo.bb05image);
                intent.putExtra("busTimeRoute", allBusesInfo.bb05_time_route);
                break;
            case "blue11":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.bb11_title);
                intent.putExtra("busImage", allBusesInfo.bb11_image);
                intent.putExtra("busTimeRoute", allBusesInfo.bb11_time_route);
                break;
            case "blue25":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.bb25_title);
                intent.putExtra("busImage", allBusesInfo.bb25_image);
                intent.putExtra("busTimeRoute", allBusesInfo.bb25_time_route);
                break;
            case "blue31":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.bb31_title);
                intent.putExtra("busImage", allBusesInfo.bb31_image);
                intent.putExtra("busTimeRoute", allBusesInfo.bb31_time_route);
                break;
            case "blue32":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.bb32_title);
                intent.putExtra("busImage", allBusesInfo.bb32_image);
                intent.putExtra("busTimeRoute", allBusesInfo.bb32_time_route);
                break;
            case "blue33":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.bb33_title);
                intent.putExtra("busImage", allBusesInfo.bb33_image);
                intent.putExtra("busTimeRoute", allBusesInfo.bb33_time_route);
                break;
            case "staff1":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.sb1_title);
                intent.putExtra("busImage", allBusesInfo.sb1_image);
                intent.putExtra("busTimeRoute", allBusesInfo.sb1_time_route);
                break;
            case "eve1":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.eb1_title);
                intent.putExtra("busImage", allBusesInfo.eb1_image);
                intent.putExtra("busTimeRoute", allBusesInfo.eb1_time_route);
                break;
            case "eve2":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.eb2_title);
                intent.putExtra("busImage", allBusesInfo.eb2_image);
                intent.putExtra("busTimeRoute", allBusesInfo.eb2_time_route);
                break;
            case "eve3":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.eb3_title);
                intent.putExtra("busImage", allBusesInfo.eb3_image);
                intent.putExtra("busTimeRoute", allBusesInfo.eb3_time_route);
                break;
            case "eve4":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.eb4_title);
                intent.putExtra("busImage", allBusesInfo.eb4_image);
                intent.putExtra("busTimeRoute", allBusesInfo.eb4_time_route);
                break;
            case "eve5":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.eb5_title);
                intent.putExtra("busImage", allBusesInfo.eb5_image);
                intent.putExtra("busTimeRoute", allBusesInfo.eb5_time_route);
                break;
            case "eve6":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.eb6_title);
                intent.putExtra("busImage", allBusesInfo.eb6_image);
                intent.putExtra("busTimeRoute", allBusesInfo.eb6_time_route);
                break;
            case "eve7":
                intent = new Intent(this, BusDetails.class);
                intent.putExtra("busName", allBusesInfo.eb7_title);
                intent.putExtra("busImage", allBusesInfo.eb7_image);
                intent.putExtra("busTimeRoute", allBusesInfo.eb7_time_route);
                break;
            case "busscedule":
                intent = new Intent(this, busscedule.class);
                break;
            default:
                return;
        }

        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.student_menu, menu);
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
