package com.fpt.vinmartauth.entity;

public class Address {
    String address;
    String state;
    String city;

    public Address() {
    }

    public Address(String address, String state, String city) {
        this.address = address;
        this.state = state;
        this.city = city;
    }

    public String getRoad() {
        return address;
    }

    public void setRoad(String road) {
        this.address = road;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
