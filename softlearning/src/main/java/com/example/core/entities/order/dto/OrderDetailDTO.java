package com.example.core.entities.order.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "order_details")
@IdClass(OrderDetailId.class)
public class OrderDetailDTO {

    @Id
    @Column(name = "order_id")
    private int orderID;

    @Column(name = "ref")
    @Id
    private String ref;

    @Column(name = "price")
    private double price;

    @Column(name = "discount")
    private double discount;

    @Column(name = "amount")
    private int amount;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, insertable = false, updatable = false)
    private OrderDTO order;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String ref, double price, double discount, int amount, OrderDTO order) {
        this.ref = ref;
        this.price = price;
        this.discount = discount;
        this.amount = amount;
        this.setOrder(order);
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

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
        if (order != null) {
            this.orderID = order.getOrderID();
        }
    }
}
