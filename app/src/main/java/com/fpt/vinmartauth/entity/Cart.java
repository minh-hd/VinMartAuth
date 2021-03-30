package com.fpt.vinmartauth.entity;


import com.google.firebase.Timestamp;

import java.util.Date;

public class Cart {
    private String documentID;
    private String UID;
    private boolean isCheckout;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String cartTotal; // will init when isCheckout is true

    public Cart() {
        this.createdAt = new Timestamp(new Date());
        this.updatedAt = new Timestamp(new Date());
        this.isCheckout = false;
    }

    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public boolean isCheckout() {
        return isCheckout;
    }

    public void setCheckout(boolean checkout) {
        isCheckout = checkout;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(String cartTotal) {
        this.cartTotal = cartTotal;
    }
}
