package com.fpt.vinmartauth.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fpt.vinmartauth.main_screen.MainActivity;
import com.fpt.vinmartauth.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> finish());

        Button btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
        });

        Button btnSignIn = findViewById(R.id.btnHomeSignIn);
        btnSignIn.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        TextView tvResetPassword = findViewById(R.id.txtResetPassword);
        tvResetPassword.setOnClickListener(view -> {
            Intent i = new Intent(this, ResetPasswordActivity.class);
            startActivity(i);
        });
    }

}