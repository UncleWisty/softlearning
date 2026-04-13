package com.example.core.entities.order.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "order_details")
public class OrderDetailDTO {

    @Id
    @Column(name = "ref")
    private String ref;
    @Column(name = "price")
    private double price;
    @Column(name = "discount")
    private double discount;
    @Column(name = "amount")
    private int amount;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderDTO order;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String ref, double price, double discount, int amount, OrderDTO order) {
        this.ref = ref;
        this.price = price;
        this.discount = discount;
        this.amount = amount;
        this.order = order;
    }

    // Getters and setters
    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }
}
