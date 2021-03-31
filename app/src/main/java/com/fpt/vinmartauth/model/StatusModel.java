package com.fpt.vinmartauth.model;

import com.fpt.vinmartauth.entity.OrderStatus;
import com.fpt.vinmartauth.entity.Ship;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class StatusModel {
    private final FirebaseFirestore instance = FirestoreInstance.getInstance();
    private final String SUCCESS_TAG = "Success request: ";
    private final String ERROR_TAG = "Error request: ";


    public void getOrderStatusByID(String id, GetAllStatusCallbacks callbacks){

        CollectionReference customerCollectionRef = instance.collection("status");
        Query shipQuery = customerCollectionRef.whereEqualTo("statusID", id);
        shipQuery.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot snapshot = task.getResult();
                OrderStatus status = (OrderStatus) snapshot.toObjects(OrderStatus.class);
                callbacks.onSuccess(status);

            } else  {
                callbacks.onFailed();
            }
        });

    }

    public interface GetAllStatusCallbacks {
        void onSuccess(OrderStatus status);
        void onFailed();
    }
}

