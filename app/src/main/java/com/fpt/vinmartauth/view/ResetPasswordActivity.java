package com.fpt.vinmartauth.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fpt.vinmartauth.R;
import com.fpt.vinmartauth.validation.AuthValidation;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

  private final AuthValidation authValidation = new AuthValidation();
  private ImageView btnBack;
  private Button btnResetPassword;
  private EditText edtEmail;
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
    setContentView(R.layout.activity_reset_password);

    mAuth = FirebaseAuth.getInstance();
    mAuthListener = firebaseAuth -> {};

    edtEmail = findViewById(R.id.editTextConfirmEmail);

    btnBack = findViewById(R.id.btnBack);
    btnBack.setOnClickListener(view -> finish());

    btnResetPassword = findViewById(R.id.btnResetPassword);
    btnResetPassword.setOnClickListener(view -> {
      String email = edtEmail.getText().toString().trim();

      if (!authValidation.isEmail(email)) {
        edtEmail.setError("Email không hợp lệ");
        return;
      }

      mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
        if (task.isSuccessful()) {
          Toast.makeText(ResetPasswordActivity.this, "Yêu cầu gửi lại mật khẩu đã được gửi đến email", Toast.LENGTH_LONG).show();
        } else {
          Toast.makeText(ResetPasswordActivity.this, "Có lỗi xảy ra", Toast.LENGTH_LONG).show();
        }
        finish();
      });


    });
  }
}