package com.fpt.vinmartauth.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.fpt.vinmartauth.R;

public class ResetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> finish());

        Button btnResetPassword = findViewById(R.id.btnResetPassword);
        btnResetPassword.setOnClickListener(view -> {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        });
    }
}