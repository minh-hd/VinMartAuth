package com.fpt.vinmartauth.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlaceOrder {
    @SerializedName("token")
    String token;
    @SerializedName("fullname")
    String fname;

    @SerializedName("mobile")
    String mobile;
    @SerializedName("email")
    String email;
    @SerializedName("address")
    String address;
    @SerializedName("user_id")
    String user_id;
    @SerializedName("cartitem")
    List<CartItem> cartItems;

    public PlaceOrder(String token, String fname, String mobile, String email, String address, String user_id, List<CartItem> cartItems) {
        this.token = token;
        this.fname = fname;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.user_id = user_id;
        this.cartItems = cartItems;
    }

    public PlaceOrder() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
