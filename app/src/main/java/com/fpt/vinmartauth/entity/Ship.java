package com.fpt.vinmartauth.entity;

public class Ship {
    String shipID;
    String title;
    String description;

    public Ship() {
    }

    public Ship(String shipID, String title, String description) {
        this.shipID = shipID;
        this.title = title;
        this.description = description;
    }

    public String getShipID() {
        return shipID;
    }

    public void setShipID(String shipID) {
        this.shipID = shipID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString()  {
        return this.getTitle();
    }
}
