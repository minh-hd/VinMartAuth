package com.fpt.vinmartauth.entity;

public class Category {
    private String ID;
    private String title;

    public Category(String id, String title) {
        this.ID = id;
        this.title = title;
    }

    public Category() {
    }

    public String getId() {
        return ID;
    }

    public void setId(String id) {
        this.ID = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
