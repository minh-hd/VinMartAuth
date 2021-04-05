package com.fpt.vinmartauth.entity;

public class ProfileMenuItem {
    int iconID;
    String itemName;

    public ProfileMenuItem(int iconID, String itemName) {
        this.iconID = iconID;
        this.itemName = itemName;
    }

    public int getIconID() {
        return iconID;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
