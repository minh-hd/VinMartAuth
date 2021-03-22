package com.fpt.vinmartauth.main_screen.product;

public class Product {
    private final String imageSrc;
    private final String productName;
    private final String productBrand;
    private final int productPrice;

    public Product(String imageSrc, String productName, String productBrand, int productPrice) {
        this.imageSrc = imageSrc;
        this.productName = productName;
        this.productBrand = productBrand;
        this.productPrice = productPrice;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public int getProductPrice() {
        return productPrice;
    }
}
