package com.fpt.vinmartauth.entity;

public class Order {
    private String ID;
    private String UID;
    private String address;
    private Card cardID;
    private String cart;
    private String payment;
    private String shipID;
    private String statusID;

    public Order() {
    }

    public Order(String ID, String UID, String address, Card cardID, String cart, String payment, String shipID, String statusID) {
        this.ID = ID;
        this.UID = UID;
        this.address = address;
        this.cardID = cardID;
        this.cart = cart;
        this.payment = payment;
        this.shipID = shipID;
        this.statusID = statusID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Card getCardID() {
        return cardID;
    }

    public void setCardID(Card cardID) {
        this.cardID = cardID;
    }

    public String getCart() {
        return cart;
    }

    public void setCart(String cart) {
        this.cart = cart;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
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
