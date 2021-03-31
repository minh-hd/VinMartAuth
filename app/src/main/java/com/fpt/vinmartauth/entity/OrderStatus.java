package com.fpt.vinmartauth.entity;


public class OrderStatus {

    String orderStatusID;
        String ostitle;

    public OrderStatus() {
    }

    public OrderStatus(String orderStatusID, String ostitle) {
        this.orderStatusID = orderStatusID;
        this.ostitle = ostitle;
    }

    public String getOrderStatusID() {
        return orderStatusID;
    }

    public void setOrderStatusID(String orderStatusID) {
        this.orderStatusID = orderStatusID;
    }

    public String getOstitle() {
        return ostitle;
    }

    public void setOstitle(String ostitle) {
        this.ostitle = ostitle;
    }
}
