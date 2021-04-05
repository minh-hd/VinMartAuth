package com.fpt.vinmartauth.model;

import android.annotation.SuppressLint;

import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreInstance {
  @SuppressLint("StaticFieldLeak")
  private static FirebaseFirestore instance;

  private FirestoreInstance(){
  }

  public static FirebaseFirestore getInstance() {
    if (instance == null) {
      instance = FirebaseFirestore.getInstance();
    }
    return instance;
  }
}
