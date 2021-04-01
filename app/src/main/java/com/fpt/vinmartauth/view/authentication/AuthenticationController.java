package com.fpt.vinmartauth.view.authentication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.fpt.vinmartauth.entity.User;
import com.fpt.vinmartauth.view.MainActivity;
import com.fpt.vinmartauth.view.dialog.LoadingDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class AuthenticationController {
  private FirebaseAuth mAuth;
  private LoadingDialog loadingDialog;

  public AuthenticationController() {
    mAuth = FirebaseAuth.getInstance();
  }

  public void loginWithEmail(Context context, String email, String password) {
    loadingDialog = new LoadingDialog((Activity) context);
    loadingDialog.startLoadingDialog();
    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
      loadingDialog.dismissDialog();
      if (task.isSuccessful()) {
        Log.i("Login", "OK!");
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
      } else {
        Log.i("Login", "Failed!");
        Toast toast = Toast.makeText(context, "Sai email hoặc mật khẩu", Toast.LENGTH_LONG);
        toast.show();
      }
    });
  }

  public void signUpWithEmail(Context context, User user) {
    loadingDialog = new LoadingDialog((Activity) context);
    loadingDialog.startLoadingDialog();
    mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(task -> {
      loadingDialog.dismissDialog();
      if (task.isSuccessful()) {
        FirebaseUser u = mAuth.getCurrentUser();
        Log.i("Signup", "Successfully");
        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName(user.getFullName())
                .setPhotoUri(Uri.parse(user.getProfileImageLink()))
                .build();
        u.updateProfile(profileChangeRequest);
        Toast.makeText(context, "Đăng ký thành công", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("email", user.getEmail());
        context.startActivity(intent);
      } else {
        Log.i("Signup", "Something wrong happened!");
        Toast toast = Toast.makeText(context, "Có lỗi xảy ra!", Toast.LENGTH_LONG);
        toast.show();
      }
    });

  }

  public User getCurrentUser() {
    FirebaseUser u = mAuth.getCurrentUser();
    if (u != null) {
      User user = new User();
      user.setFullName(u.getDisplayName());
      user.setEmail(u.getEmail());
      user.setPhone(u.getPhoneNumber());
      user.setProfileImageLink(u.getPhotoUrl().toString());
      return user;
    }
    return null;
  }
}
