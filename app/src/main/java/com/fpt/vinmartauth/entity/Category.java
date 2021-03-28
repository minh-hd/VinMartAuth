package com.fpt.vinmartauth.entity;

public class Category {
    private String id;
    private String title;

    public Category(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public Category() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
