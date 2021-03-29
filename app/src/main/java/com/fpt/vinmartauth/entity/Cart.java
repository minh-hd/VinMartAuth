package com.fpt.vinmartauth.entity;

public class Cart {
    String cartID;
    Customer userID;
    boolean isCheckout;

    public Cart() {
    }

    public Cart(String cartID, Customer userID, boolean isCheckout) {
        this.cartID = cartID;
        this.userID = userID;
        this.isCheckout = isCheckout;
    }

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    public Customer getUserID() {
        return userID;
    }

    public void setUserID(Customer userID) {
        this.userID = userID;
    }

    public boolean isCheckout() {
        return isCheckout;
    }

    public void setCheckout(boolean checkout) {
        isCheckout = checkout;
    }
}
