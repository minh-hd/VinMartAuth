package com.fpt.vinmartauth.model;

import com.fpt.vinmartauth.entity.Order;
import com.fpt.vinmartauth.entity.OrderStatus;
import com.fpt.vinmartauth.entity.Payment;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class OrderStatusModel {
    private final FirebaseFirestore instance = FirestoreInstance.getInstance();
    private final String SUCCESS_TAG = "Success request: ";
    private final String ERROR_TAG = "Error request: ";

    public void getAllStatus(OrderStatusModel.GetAllStatusCallbacks callbacks){
        CollectionReference statusCollectionRef = instance.collection("status");
        statusCollectionRef.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                QuerySnapshot snapshot = task.getResult();
                List<OrderStatus> statuses = new ArrayList<>();
                for (QueryDocumentSnapshot document: snapshot) {
                    OrderStatus os = document.toObject(OrderStatus.class);
                    statuses.add(os);
                }
                callbacks.onSuccess(statuses);
            }else {
                callbacks.onFailed();
            }
        });
    }

    public interface GetAllStatusCallbacks {
        void onSuccess(List<OrderStatus> statuses);
        void onFailed();
    }
}
