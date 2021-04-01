package com.fpt.vinmartauth.view.authentication;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.fpt.vinmartauth.entity.User;
import com.fpt.vinmartauth.view.dialog.LoadingDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class UserController {
  private FirebaseAuth mAuth;
  private LoadingDialog loadingDialog;

  public UserController() {
    mAuth = FirebaseAuth.getInstance();
  }

  public void updateUserProfile(Context context, User u) {
    loadingDialog = new LoadingDialog((Activity) context);
    FirebaseUser user = mAuth.getCurrentUser();
    UserProfileChangeRequest changeRequest = new UserProfileChangeRequest.Builder().setDisplayName(u.getFullName()).build();
    user.updateProfile(changeRequest);
    loadingDialog.startLoadingDialog();
    mAuth.updateCurrentUser(user).addOnCompleteListener(task -> {
      loadingDialog.dismissDialog();
      if (task.isSuccessful()) {
        Toast.makeText(context, "Lưu thành công", Toast.LENGTH_LONG).show();
      } else {
         Toast.makeText(context, "Lưu thất bại!\nVui lòng thử lại sau", Toast.LENGTH_LONG).show();
      }
    });
  }

  public FirebaseAuth getmAuth() {
    return mAuth;
  }

}
