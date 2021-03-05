package com.fpt.vinmartauth.login;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.fpt.vinmartauth.R;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> finish());
    }
}