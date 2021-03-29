package com.fpt.vinmartauth.entity;

import java.io.Serializable;
import java.util.Collections;

public class Product implements Serializable, Comparable<Product> {
    private String id;
    private Category category;
    private String description;
    private String image;
    private int price;
    private String quantity;
    private String title;
    private String vendor;

    public Product(String id, Category category, String description, String image, int price, String quantity, String title, String vendor) {
        this.id = id;
        this.category = category;
        this.description = description;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.title = title;
        this.vendor = vendor;
    }

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", quantity='" + quantity + '\'' +
                ", title='" + title + '\'' +
                ", vendor='" + vendor + '\'' +
                '}';
    }

    @Override
    public int compareTo(Product o) {
        if(price == o.price){
            return 0;
        } else if(price > o.price ){
            return 1;
        } else {
            return -1;
        }
    }
}
