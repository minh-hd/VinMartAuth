package com.fpt.vinmartauth.entity;

public class Payment {
    String paymentID;
    String cardID;
    String customerID;
    String description;
    String title;

    public Payment() {
    }

    public Payment(String paymentID, String cardID, String customerID, String description, String title) {
        this.paymentID = paymentID;
        this.cardID = cardID;
        this.customerID = customerID;
        this.description = description;
        this.title = title;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
