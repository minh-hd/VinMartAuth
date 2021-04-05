package com.fpt.vinmartauth.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fpt.vinmartauth.R;
import com.fpt.vinmartauth.validation.AuthValidation;
import com.fpt.vinmartauth.view.authentication.AuthenticationController;

public class LoginActivity extends AppCompatActivity {

  private final AuthValidation authValidation = new AuthValidation();
  private ImageView btnBack;
  private Button btnSignIn;
  private TextView tvResetPassword;
  private TextView tvSignUp;
  private EditText edtEmail;
  private EditText edtPassword;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    edtEmail = findViewById(R.id.editTextTextEmailAddress);
    edtPassword = findViewById(R.id.editTextTextPassword);
    btnBack = findViewById(R.id.btnBack);
    btnBack.setOnClickListener(view -> finish());

    btnSignIn = findViewById(R.id.btnHomeSignIn);
    btnSignIn.setOnClickListener(view -> {
      String email = edtEmail.getText().toString().toLowerCase().trim();
      String password = edtPassword.getText().toString().trim();
      if (!authValidation.isEmail(email)) {
        edtEmail.setError("Email không hợp lệ");
        return;
      }
      if (!authValidation.isValidPassword(password)) {
        edtPassword.setError("Mật khẩu không hợp lệ");
        return;
      }

      InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
      new AuthenticationController().loginWithEmail(this, email, password);

    });

    tvResetPassword = findViewById(R.id.txtResetPassword);
    tvResetPassword.setOnClickListener(view -> {
      Intent i = new Intent(this, ResetPasswordActivity.class);
      startActivity(i);
    });

    tvSignUp = findViewById(R.id.tvSignUp);
    tvSignUp.setOnClickListener(view -> {
      Intent intent = new Intent(this, SignupActivity.class);
      startActivity(intent);
    });

  }


}