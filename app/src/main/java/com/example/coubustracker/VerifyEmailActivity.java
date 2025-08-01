package com.example.coubustracker;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class VerifyEmailActivity extends AppCompatActivity {

    private EditText etEmail;
    private Button btnSendReset;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email);

        // ✅ Setup Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Reset Password");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // ✅ Status Bar - Light mode handling
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
        }

        // ✅ Initialize Firebase
        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.etEmail);
        btnSendReset = findViewById(R.id.btnSendReset);

        btnSendReset.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();

            if (email.isEmpty()) {
                etEmail.setError("Please enter your email");
                etEmail.requestFocus();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.setError("Enter a valid email address");
                etEmail.requestFocus();
                return;
            }

            // ✅ Allow only specific driver email to reset
            if (!email.equals("coubustracker@gmail.com")) {
                Toast.makeText(this, "Only driver account is allowed", Toast.LENGTH_SHORT).show();
                return;
            }

            // ✅ Send password reset email
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Reset link sent. Check your email.", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(VerifyEmailActivity.this, LoginActivity.class));
                    finish();
                } else {
                    String msg = "Failed to send reset email";
                    if (task.getException() instanceof FirebaseAuthInvalidUserException) {
                        msg = "Email not registered";
                    }
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
