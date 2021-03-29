package com.fpt.vinmartauth.model;

import com.google.firebase.firestore.FirebaseFirestore;
import android.annotation.SuppressLint;


public class FirestoreInstance {
  @SuppressLint("StaticFieldLeak")
  private static FirebaseFirestore instance;

  public FirestoreInstance() {
  }

  public static FirebaseFirestore  getInstance() {
    if (instance == null) {
      instance = FirebaseFirestore.getInstance();
    }
    return instance;
  }
}
