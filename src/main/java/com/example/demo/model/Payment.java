package com.example.demo.model;

public class Payment {
    private Double totalPrice;

    public Payment(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Payment() {
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
