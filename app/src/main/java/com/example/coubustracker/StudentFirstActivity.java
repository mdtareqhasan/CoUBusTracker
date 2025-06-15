package com.example.coubustracker;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowInsetsController;

import androidx.appcompat.app.AppCompatActivity;

public class StudentFirstActivity extends AppCompatActivity {

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
            getSupportActionBar().setTitle("Student Bus List");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void openLogin(View view) {
        Object tagObj = view.getTag();
        if (tagObj == null) return;

        String role = tagObj.toString();
        Intent intent;

        switch (role) {
            case "red2": intent = new Intent(this, studentredbus2.class); break;
            case "red3": intent = new Intent(this, studentredbus3.class); break;
            case "red4": intent = new Intent(this, studentredbus4.class); break;
            case "red5": intent = new Intent(this, studentredbus5.class); break;
            case "red6": intent = new Intent(this, studentredbus6.class); break;
            case "red7": intent = new Intent(this, studentredbus7.class); break;
            case "red8": intent = new Intent(this, studentredbus8.class); break;
            case "red10": intent = new Intent(this, studentredbus10.class); break;
            case "blue3": intent = new Intent(this, studentbluebus3.class); break;
            case "blue4": intent = new Intent(this, studentbluebus4.class); break;
            case "blue5": intent = new Intent(this, studentbluebus5.class); break;
            case "blue11": intent = new Intent(this, studentbluebus11.class); break;
            case "blue25": intent = new Intent(this, studentbluebus25.class); break;
            case "blue31": intent = new Intent(this, studentbluebus31.class); break;
            case "blue32": intent = new Intent(this, studentbluebus32.class); break;
            case "blue33": intent = new Intent(this, studentbluebus33.class); break;
            case "eve1": intent = new Intent(this, studentevebus1.class); break;
            case "eve2": intent = new Intent(this, studentevebus2.class); break;
            case "eve3": intent = new Intent(this, studentevebus3.class); break;
            case "eve4": intent = new Intent(this, studentevebus4.class); break;
            case "eve5": intent = new Intent(this, studentevebus5.class); break;
            case "eve6": intent = new Intent(this, studentevebus6.class); break;
            case "eve7": intent = new Intent(this, studentevebus7.class); break;
            case "busscedule": intent = new Intent(this, busscedule.class); break;
            default: return;
        }

        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    // Inflate menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.student_menu, menu);
        return true;
    }

    // Handle menu item click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_about_us) {
            startActivity(new Intent(this, AboutUsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
