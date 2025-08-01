package com.example.coubustracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowInsetsController;
import android.widget.*;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText etDriverId, etPassword;
    private Button btnContinue;
    private TextView tvForgotPassword;
    private SharedPreferences sharedPreferences;

    private static final String PREF_NAME = "DriverPrefs";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASS = "password";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // ✅ Action Bar Setup
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Driver Login");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // ✅ Status Bar Color (Light/Dark mode support)
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

        // ✅ Init Views & Auth
        etDriverId = findViewById(R.id.etDriverId);
        etPassword = findViewById(R.id.etPassword);
        btnContinue = findViewById(R.id.btnContinue);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        mAuth = FirebaseAuth.getInstance();

        // ✅ Auto-fill saved credentials
        String savedEmail = sharedPreferences.getString(KEY_EMAIL, "");
        String savedPass = sharedPreferences.getString(KEY_PASS, "");
        etDriverId.setText(savedEmail);
        etPassword.setText(savedPass);

        // ✅ Login Button
        btnContinue.setOnClickListener(v -> {
            String email = etDriverId.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etDriverId.setError("Enter valid email");
                etDriverId.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                etPassword.setError("Enter password");
                etPassword.requestFocus();
                return;
            }

            // ✅ Optional: Restrict login only to CoU Bus Tracker driver email
            if (!email.equals("coubustracker@gmail.com")) {
                Toast.makeText(this, "Only CoU Bus Tracker driver can log in", Toast.LENGTH_SHORT).show();
                return;
            }

            // ✅ Firebase Auth Login
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // ✅ Save credentials
                    sharedPreferences.edit()
                            .putString(KEY_EMAIL, email)
                            .putString(KEY_PASS, password)
                            .apply();

                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, DriverFirstActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });

        // ✅ Forgot Password
        tvForgotPassword.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, VerifyEmailActivity.class));
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
