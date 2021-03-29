package com.fpt.vinmartauth.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fpt.vinmartauth.R;
import com.fpt.vinmartauth.validation.AuthValidation;
import com.fpt.vinmartauth.view.dialog.LoadingDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

  private final AuthValidation authValidation = new AuthValidation();
  private ImageView btnBack;
  private Button btnSignIn;
  private TextView tvResetPassword;
  private TextView tvSignUp;
  private EditText edtEmail;
  private EditText edtPassword;
  private LoadingDialog loadingDialog;
  private FirebaseAuth mAuth;
  private FirebaseAuth.AuthStateListener mAuthListener;

  @Override
  protected void onStart() {
    super.onStart();
    mAuth.addAuthStateListener(mAuthListener);
  }

  @Override
  protected void onStop() {
    super.onStop();
    if (mAuthListener != null) {
      mAuth.removeAuthStateListener(mAuthListener);
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    loadingDialog = new LoadingDialog(this);

    mAuth = FirebaseAuth.getInstance();
    mAuthListener = firebaseAuth -> {
      FirebaseUser user = firebaseAuth.getCurrentUser();
      if (user != null) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
      }
    };

    btnBack = findViewById(R.id.btnBack);
    btnBack.setOnClickListener(view -> finish());

    btnSignIn = findViewById(R.id.btnHomeSignIn);
    btnSignIn.setOnClickListener(view -> {

      String email = edtEmail.getText().toString().trim();
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
      loadingDialog.startLoadingDialog();
      mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
        loadingDialog.dismissDialog();
        if (task.isSuccessful()) {
          Log.i("Login", "OK!");
          Intent intent = new Intent(LoginActivity.this, MainActivity.class);
          startActivity(intent);
        } else {
          Log.i("Login", "Failed!");
          Toast toast = Toast.makeText(LoginActivity.this, "Sai email hoặc mật khẩu", Toast.LENGTH_LONG);
          toast.show();
        }
      });


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

    edtEmail = findViewById(R.id.editTextTextEmailAddress);
    edtPassword = findViewById(R.id.editTextTextPassword);
    TextWatcher textWatcher = new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        edtEmail.setError(null);
        edtPassword.setError(null);
      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (email.equals("")) {
          edtEmail.setError(null);
        }

        if (password.equals("")) {
          edtPassword.setError(null);
        }

      }

      @Override
      public void afterTextChanged(Editable s) {
      }
    };
    edtEmail.addTextChangedListener(textWatcher);

    edtPassword.addTextChangedListener(textWatcher);
  }


}