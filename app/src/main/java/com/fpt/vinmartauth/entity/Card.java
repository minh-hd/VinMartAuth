package com.fpt.vinmartauth.entity;

public class Card {
    String cardID;
    String expDate;
    String cardNumber;
    String cardOwner;
    String cardType;

    public Card() {
    }

    public Card(String cardID, String expDate, String cardNumber, String cardOwner, String cardType) {
        this.cardID = cardID;
        this.expDate = expDate;
        this.cardNumber = cardNumber;
        this.cardOwner = cardOwner;
        this.cardType = cardType;
    }

}
