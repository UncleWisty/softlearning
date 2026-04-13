package com.example.core.entities.order.dto;

import java.io.Serializable;
import java.util.Objects;

public class OrderDetailId implements Serializable {

    private int orderID;
    private String ref;

    public OrderDetailId() {
    }

    public OrderDetailId(int orderID, String ref) {
        this.orderID = orderID;
        this.ref = ref;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderDetailId that = (OrderDetailId) o;
        return orderID == that.orderID && Objects.equals(ref, that.ref);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, ref);
    }
}