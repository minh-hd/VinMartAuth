package com.fpt.vinmartauth.model;

import android.util.Log;

import com.fpt.vinmartauth.entity.Cart;
import com.fpt.vinmartauth.entity.Order;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class OrderModel {
    private final FirebaseFirestore instance = FirestoreInstance.getInstance();
    private final String SUCCESS_TAG = "Success request: ";
    private final String ERROR_TAG = "Error request: ";
    public void getAllOrder(GetAllOrdersCallbacks callbacks) {
        CollectionReference orderCollectionRef = instance.collection("orders");
        orderCollectionRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot snapshot = task.getResult();
                List<Order> orders = new ArrayList<>();
                for (QueryDocumentSnapshot document : snapshot) {
                    Order order = document.toObject(Order.class);
                    order.setOrderId(document.getId());
                    orders.add(order);
                    Log.d("anhdt", order.toString());
                }
                callbacks.onSuccess(orders);
            } else {
                callbacks.onFailed();
            }
        });
    }

    public void getOrderByCartId(Cart cart, GetOrderByCartCallbacks callbacks) {
        getAllOrder(new GetAllOrdersCallbacks() {
            @Override
            public void onSuccess(List<Order> orders) {
                List<Order> filteredOrders = new ArrayList<>();
                orders.forEach(order -> {
                    if (order.getCart().getDocumentID().contains(cart.getDocumentID())) {
                        filteredOrders.add(order);
                    }
                });
                callbacks.onSuccess(filteredOrders);

            }
            @Override
            public void onFailed() {
                callbacks.onFailed();
            }
        });
    }
    public interface GetOrderByCartCallbacks {
        void onSuccess(List<Order> orders);
        void onFailed();
    }
    public interface GetAllOrdersCallbacks {
        void onSuccess(List<Order> orders);
        void onFailed();
    }
}

