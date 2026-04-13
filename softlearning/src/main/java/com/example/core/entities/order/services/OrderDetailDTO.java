package com.example.core.entities.order.services;

public class OrderDetailDTO {

    private String ref;
    private double price;
    private double discount;
    private int amount;

    public OrderDetailDTO(String ref, double price, double discount, int amount) {
        this.ref = ref;
        this.price = price;
        this.discount = discount;
        this.amount = amount;
    }

    public OrderDetailDTO() {
    }

    public String getRef() {
        return ref;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public int getAmount() {
        return amount;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
