package com.fpt.vinmartauth.entity;

public class CartItem {
    private String documentID;
    private String productID;
    private String productImage;
    private String productTitle;
    private String productPrice;
    private String quantity;

    @Override
    public String toString() {
        return "CartItem{" +
                "documentID='" + documentID + '\'' +
                ", productID='" + productID + '\'' +
                ", productImage='" + productImage + '\'' +
                ", productTitle='" + productTitle + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }

    public CartItem() {
    }

    public CartItem(String documentID, String productID, String productImage, String productTitle, String productPrice, String quantity) {
        this.documentID = documentID;
        this.productID = productID;
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}

