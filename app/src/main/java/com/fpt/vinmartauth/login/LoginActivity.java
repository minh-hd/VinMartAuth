package com.fpt.vinmartauth.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fpt.vinmartauth.MainActivity;
import com.fpt.vinmartauth.R;

public class LoginActivity extends AppCompatActivity {

  ImageView btnBack;
  Button btnSignIn;
  TextView tvResetPassword;
  EditText edtEmail;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    btnBack = findViewById(R.id.btnBack);
    btnBack.setOnClickListener(view -> finish());

    btnSignIn = findViewById(R.id.btnHomeSignIn);
    btnSignIn.setOnClickListener(view -> {
      Intent intent = new Intent(this, MainActivity.class);
      startActivity(intent);
    });

    tvResetPassword = findViewById(R.id.txtResetPassword);
    tvResetPassword.setOnClickListener(view -> {
      Intent i = new Intent(this, ResetPasswordActivity.class);
      startActivity(i);
    });

    edtEmail = findViewById(R.id.editTextTextEmailAddress);
    edtEmail.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Log.d("EditTextEmail Before", "");
      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

        Log.d("EditTextEmail Current", s.toString());
      }

      @Override
      public void afterTextChanged(Editable s) {

        Log.d("EditTextEmail After", "");
      }
    });
  }

}