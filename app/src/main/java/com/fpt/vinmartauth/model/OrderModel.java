package com.fpt.vinmartauth.model;

import com.google.firebase.firestore.FirebaseFirestore;

public class OrderModel {
    private final FirebaseFirestore instance = FirestoreInstance.getInstance();
    private final String SUCCESS_TAG = "Success request: ";
    private final String ERROR_TAG = "Error request: ";

}

