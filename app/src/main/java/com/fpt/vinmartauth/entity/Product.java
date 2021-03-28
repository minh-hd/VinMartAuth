package com.fpt.vinmartauth.entity;

public class Product {
    private String ID;
    private String category;
    private String description;
    private String image;
    private int price;
    private String quantity;
    private String title;
    private String vendor;

    public Product(String ID, String category, String description, String image, int price, String quantity, String title, String vendor) {
        this.ID = ID;
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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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
}
