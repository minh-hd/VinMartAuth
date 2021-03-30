package com.fpt.vinmartauth.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.fpt.vinmartauth.entity.Customer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class CustomerModel {
  private final FirebaseFirestore instance = FirestoreInstance.getInstance();
  private final String SUCCESS_TAG = "Success request: ";
  private final String ERROR_TAG = "Error request: ";

  public Customer getCustomerByEmail(String email) {
    CollectionReference customerCollectionRef = instance.collection("customers");
    Query customerQuery = customerCollectionRef.whereEqualTo("email", email);
    customerQuery.get().addOnCompleteListener(task -> {
      if (task.isSuccessful()) {

        Log.i(SUCCESS_TAG, "OK");
      } else  {
        Log.d(ERROR_TAG, "Something wrong happened");
      }
    });
    return null;
  }
}
