package com.fpt.vinmartauth.entity;

import com.google.gson.annotations.SerializedName;

public class OrderStatus {
    @SerializedName("statusID")
    String orderStatusID;
    @SerializedName("title")
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
