package com.fpt.vinmartauth.model;

import android.util.Log;

import com.fpt.vinmartauth.entity.Order;
import com.fpt.vinmartauth.entity.OrderStatus;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderModel {
    private final FirebaseFirestore instance = FirestoreInstance.getInstance();
    private final String SUCCESS_TAG = "Success request: ";
    private final String ERROR_TAG = "Error request: ";
    private final FirebaseFirestore db;

    public OrderModel() {

        db = null;
    }



    private List<Order> populateOrder(QuerySnapshot collection) {
        List<Order> orders = new ArrayList<>();
        for (DocumentSnapshot doc : collection) {
            Order o = doc.toObject(Order.class);

            orders.add(o);
        }
        return orders;
    }
    public void createOrder(Order orderDTO, CreateOrdersCallbacks callbacks) {
        CollectionReference cartRef = instance.collection("orders");
        cartRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot snapshot = task.getResult();
                List<Order> allOrder = populateOrder(snapshot);
                Map<String,Object> orders = new HashMap<>();
                orders.put("ID",orderDTO.getID());
                orders.put("UID", orderDTO.getUID());
                orders.put("address", orderDTO.getAddress());
                orders.put("cardID", orderDTO.getCardID());
                orders.put("cart", orderDTO.getCart());
                orders.put("payment", orderDTO.getPayment());
                orders.put("shipID", orderDTO.getShipID());
                orders.put("status", orderDTO.getStatusID());
                db.collection("orders").document("Ox".concat(String.format("%03d" , allOrder.size() + 1)))
                        .set(orders).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("anhdt", "Order successfully written!");
                    }
                });
                callbacks.onSuccess();

            } else  {
                callbacks.onFailed();
            }
        });



    }


    public interface CreateOrdersCallbacks {
        void onSuccess();
        void onFailed();
    }

    public interface getAllMOrdersCallBacks{
        void onSuccess(List<Order> orders);
        void onFailed();
    }

    public void getAllOrders(getAllMOrdersCallBacks callBacks){
        CollectionReference orderRef = instance.collection("orders");
        orderRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot snapshot = task.getResult();
                List<Order> orders = new ArrayList<>();
                for (QueryDocumentSnapshot document : snapshot) {
                    Order order = document.toObject(Order.class);
                    orders.add(order);
                    Log.d("anhntl", order.toString());
                }
                callBacks.onSuccess(orders);
            } else {
                callBacks.onFailed();
            }
        });
    }
}

