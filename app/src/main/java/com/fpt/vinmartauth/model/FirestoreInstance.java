package com.fpt.vinmartauth.model;

import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreInstance {
  private static FirestoreInstance instance;
  private FirebaseFirestore db;

  private FirestoreInstance(){
    if (db == null) {
      db = FirebaseFirestore.getInstance();
    }
  }

  public static FirestoreInstance getInstance() {
    if (instance == null) {
      instance = new FirestoreInstance();
    }
    return instance;
  }
}
