package com.fpt.vinmartauth.entity;

public class Card {
    String cardID;
    String expDate;
    String cardNumber;
    String cardOwner;


    public Card() {
    }

    public Card(String cardID, String expDate, String cardNumber, String cardOwner) {
        this.cardID = cardID;
        this.expDate = expDate;
        this.cardNumber = cardNumber;
        this.cardOwner = cardOwner;

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
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardOwner() {
        return cardOwner;
    }

    public void setCardOwner(String cardOwner) {
        this.cardOwner = cardOwner;
    }
}
