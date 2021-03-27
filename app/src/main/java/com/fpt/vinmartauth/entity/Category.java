package com.fpt.vinmartauth.entity;

public class Category {
    private String Id;
    private String title;

    public Category(String id, String title) {
        Id = id;
        this.title = title;
    }

    public Category() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
