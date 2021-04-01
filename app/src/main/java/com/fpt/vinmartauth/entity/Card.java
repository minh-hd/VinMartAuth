package com.fpt.vinmartauth.entity;

public class Card {
    String cardID;
    String expDate;
    String no;
    String owner;


    public Card() {
    }

    public Card(String cardID, String expDate, String cardNumber, String cardOwner) {
        this.cardID = cardID;
        this.expDate = expDate;
        this.no = cardNumber;
        this.owner = cardOwner;

    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getCardNumber() {
        return no;
    }

    public void setCardNumber(String cardNumber) {
        this.no = cardNumber;
    }

    public String getCardOwner() {
        return owner;
    }

    public void setCardOwner(String cardOwner) {
        this.owner = cardOwner;
    }
}
