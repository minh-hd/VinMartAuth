package com.fpt.vinmartauth.entity;

public class Order {
    String orderId;
    Customer customer;
    Cart cart;
    Payment payment;
    Ship ship;
    OrderStatus status;

    public Order() {
    }

    public Order(String orderId) {
        this.orderId = orderId;
    }

    public Order(String orderId, Customer customerID, Cart cartID, Payment paymentID, Ship shipID, OrderStatus statusID) {
        this.orderId = orderId;
        this.customer = customerID;
        this.cart = cartID;
        this.payment = paymentID;
        this.ship = shipID;
        this.status = statusID;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
