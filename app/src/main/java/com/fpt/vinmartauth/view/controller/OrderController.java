package com.fpt.vinmartauth.view.controller;

import com.fpt.vinmartauth.entity.Order;
import com.fpt.vinmartauth.model.OrderModel;
import com.fpt.vinmartauth.view.OrderView;

import java.util.List;

public class OrderController {
    private OrderView view;
    private final OrderModel orderModel = new OrderModel();

    public void setView(OrderView view){
        this.view = view;
    }

    public void getAllOrder(){
        orderModel.getAllOrders(new OrderModel.getAllMOrdersCallBacks() {
            @Override
            public void onSuccess(List<Order> orders) {
                view.setOrders(orders);
            }

            @Override
            public void onFailed() {

            }
        });
    }
}
