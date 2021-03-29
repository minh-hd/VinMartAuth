package com.fpt.vinmartauth.model;

import com.fpt.vinmartauth.entity.Payment;
import com.fpt.vinmartauth.entity.Ship;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PaymentModel {
    private final FirebaseFirestore instance = FirestoreInstance.getInstance();
    private final String SUCCESS_TAG = "Success request: ";
    private final String ERROR_TAG = "Error request: ";

    public void getAllPay(PaymentModel.GetAllPayCallbacks callbacks){
        CollectionReference payCollectionRef = instance.collection("payments");
        payCollectionRef.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                QuerySnapshot snapshot = task.getResult();
                List<Payment> payments = new ArrayList<>();
                for (QueryDocumentSnapshot document: snapshot) {
                    Payment pay = document.toObject(Payment.class);
                    payments.add(pay);
                }
                callbacks.onSuccess(payments);
            }else {
                callbacks.onFailed();
            }
        });
    }

    public interface GetAllPayCallbacks {
        void onSuccess(List<Payment> payment);
        void onFailed();
    }
}
