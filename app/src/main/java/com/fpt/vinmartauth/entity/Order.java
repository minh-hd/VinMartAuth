package com.fpt.vinmartauth.entity;

public class Order {
    String orderId;
    String customerID;
    String cartID;
    String paymentID;
    String shipID;
    String statusID;

    public Order() {
    }

    public Order(String orderId) {
        this.orderId = orderId;
    }

    public Order(String orderId, String customerID, String cartID, String paymentID, String shipID, String statusID) {
        this.orderId = orderId;
        this.customerID = customerID;
        this.cartID = cartID;
        this.paymentID = paymentID;
        this.shipID = shipID;
        this.statusID = statusID;
    }



    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getShipID() {
        return shipID;
    }

    public void setShipID(String shipID) {
        this.shipID = shipID;
    }

    public String getStatusID() {
        return statusID;
    }

    public void setStatusID(String statusID) {
        this.statusID = statusID;
    }
}
