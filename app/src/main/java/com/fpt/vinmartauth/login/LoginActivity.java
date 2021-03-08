package com.fpt.vinmartauth.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fpt.vinmartauth.MainActivity;
import com.fpt.vinmartauth.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnSignUp = findViewById(R.id.btnSignUp);
        Button btnSignIn = findViewById(R.id.btnSignIn);
        TextView tvResetPassword = findViewById(R.id.txtResetPassword);
        btnSignUp.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
        });
        btnSignIn.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        tvResetPassword.setOnClickListener(view -> {
            Intent i = new Intent(this, ResetPasswordActivity.class);
            startActivity(i);
        });
    }

}