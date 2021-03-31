package com.fpt.vinmartauth.controller;

import com.fpt.vinmartauth.entity.CartItem;
import com.fpt.vinmartauth.entity.Order;
import com.fpt.vinmartauth.model.CartItemModel;
import com.fpt.vinmartauth.model.OrderModel;

import java.util.List;

public class CreateOrderController {
    private CreateOrderController.OrderView view;

    private final OrderModel orderModel = new OrderModel();

    public void createOrder(Order order) {
        orderModel.createOrder(order, new OrderModel.CreateOrdersCallbacks() {
            @Override
            public void onSuccess() {
                view.createOrderMessage("Success");
            }

            @Override
            public void onFailed() {

            }
        });
    }
    public interface OrderView {
        void createOrderMessage(String message);


    }
}
