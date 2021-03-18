package com.fpt.vinmartauth.main_screen.product;

public class Product {
    int imageSrc;
    String productName;
    String productBrand;
    double productPrice;

    public Product(int imageSrc, String productName, String productBrand, double productPrice) {
        this.imageSrc = imageSrc;
        this.productName = productName;
        this.productBrand = productBrand;
        this.productPrice = productPrice;
    }

    public int getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(int imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
