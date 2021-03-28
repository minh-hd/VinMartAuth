package com.fpt.vinmartauth.entity;

import java.util.Date;
import java.util.Map;

public class CartItem {
    private String document;
    private String cartID;
    private Date createdAt;
    private String description;
    private Map<String, String> product;
    private int quantity;
    private Date updatedAt;

    public CartItem() {

    }

    public CartItem(String document, String cartID, Date createdAt, String description,
                    Map<String, String> product, int quantity, Date updatedAt) {
        this.document = document;
        this.cartID = cartID;
        this.createdAt = createdAt;
        this.description = description;
        this.product = product;
        this.quantity = quantity;
        this.updatedAt = updatedAt;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getProduct() {
        return product;
    }

    public void setProduct(Map<String, String> product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}

