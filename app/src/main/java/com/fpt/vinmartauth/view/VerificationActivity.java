package com.fpt.vinmartauth.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.fpt.vinmartauth.R;

public class VerificationActivity extends AppCompatActivity {
    private ImageView btnBack;
    private Button btnVerify;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);


        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> finish());
        btnVerify = findViewById(R.id.btnVerify);
        btnVerify.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}