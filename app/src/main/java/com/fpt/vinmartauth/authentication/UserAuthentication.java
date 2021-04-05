package com.fpt.vinmartauth.authentication;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserAuthentication {

  private FirebaseAuth mAuth;

  public UserAuthentication() {
    mAuth = FirebaseAuth.getInstance();
  }

  public boolean isLoggedIn() {
    return mAuth.getCurrentUser() != null;
  }
}
