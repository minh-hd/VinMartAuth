package com.fpt.vinmartauth.view;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import com.fpt.vinmartauth.R;
import com.fpt.vinmartauth.entity.User;
import com.fpt.vinmartauth.validation.AuthValidation;
import com.fpt.vinmartauth.view.authentication.AuthenticationController;

public class SignupActivity extends AppCompatActivity {

  private final AuthValidation authValidation = new AuthValidation();
  private ImageView btnBack;
  private Button btnSignUp;
  private EditText edtFullname;
  private EditText edtEmail;
  private EditText edtPassword;
  private final String DEFAULT_IMAGE_LINK = "https://firebasestorage.googleapis.com/v0/b/vinmartauth.appspot.com/o/default-profile-icon-16.jpg?alt=media";



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signup);

    btnBack = findViewById(R.id.btnBack);
    btnBack.setOnClickListener(view -> finish());

    edtFullname = findViewById(R.id.etRegName);
    edtEmail = findViewById(R.id.etRegEmail);
    edtPassword = findViewById(R.id.etRegPassword);

    btnSignUp = findViewById(R.id.btnSignUp);
    btnSignUp.setOnClickListener(view -> {

      String name =  edtFullname.getText().toString().trim();
      String email = edtEmail.getText().toString().toLowerCase().trim();
      String password = edtPassword.getText().toString().trim();

      if (name.equals("")) {
        edtFullname.setError("Tên không được bỏ trống");
        return;
      }
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
      User u = new User();
      u.setEmail(email);
      u.setFullName(name);
      u.setProfileImageLink(DEFAULT_IMAGE_LINK);
      u.setPassword(password);
      new AuthenticationController().signUpWithEmail(this, u);
    });
  }
}