package com.fpt.vinmartauth.model;

import com.fpt.vinmartauth.entity.Ship;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShipModel {
    private final FirebaseFirestore instance = FirestoreInstance.getInstance();
    private final String SUCCESS_TAG = "Success request: ";
    private final String ERROR_TAG = "Error request: ";

    public void getAllShip(ShipModel.GetAllShipCallbacks callbacks){
        CollectionReference shipCollectionRef = instance.collection("shippings");
        shipCollectionRef.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                QuerySnapshot snapshot = task.getResult();
                List<Ship> shipments = new ArrayList<>();
                for (QueryDocumentSnapshot document: snapshot) {
                    Ship ship = document.toObject(Ship.class);
                    shipments.add(ship);
                }
                callbacks.onSuccess(shipments);
            }else {
                callbacks.onFailed();
            }
        });
    }

    public interface GetAllShipCallbacks {
        void onSuccess(List<Ship> shipment);
        void onFailed();
    }
}
