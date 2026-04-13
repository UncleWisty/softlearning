package com.example.core.entities.order.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderDTO {

    @Id
    @Column(name = "order_id")
    private int orderID;
    @Column(name = "client_id")
    private int clientID;
    @Column(name = "receiver_address")
    private String receiverAddress;
    @Column(name = "receiver_person")
    private String receiverPerson;
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;
    @Column(name = "phone_contacts")
    private String phoneContacts; // Serialized Set<String>
    @Column(name = "package_weight")
    private double packageWeight;
    @Column(name = "package_height")
    private double packageHeight;
    @Column(name = "package_width")
    private double packageWidth;
    @Column(name = "package_depth")
    private double packageDepth;
    @Column(name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderDetailDTO> shopCart;

    public OrderDTO() {
    }

    public OrderDTO(int orderID, int clientID, String receiverAddress, String receiverPerson, LocalDateTime paymentDate, LocalDateTime deliveryDate, String phoneContacts, double packageWeight, double packageHeight, double packageWidth, double packageDepth, String status, List<OrderDetailDTO> shopCart) {
        this.orderID = orderID;
        this.clientID = clientID;
        this.receiverAddress = receiverAddress;
        this.receiverPerson = receiverPerson;
        this.paymentDate = paymentDate;
        this.deliveryDate = deliveryDate;
        this.phoneContacts = phoneContacts;
        this.packageWeight = packageWeight;
        this.packageHeight = packageHeight;
        this.packageWidth = packageWidth;
        this.packageDepth = packageDepth;
        this.status = status;
        this.shopCart = shopCart;
    }

    // Getters and setters
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverPerson() {
        return receiverPerson;
    }

    public void setReceiverPerson(String receiverPerson) {
        this.receiverPerson = receiverPerson;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getPhoneContacts() {
        return phoneContacts;
    }

    public void setPhoneContacts(String phoneContacts) {
        this.phoneContacts = phoneContacts;
    }

    public double getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(double packageWeight) {
        this.packageWeight = packageWeight;
    }

    public double getPackageHeight() {
        return packageHeight;
    }

    public void setPackageHeight(double packageHeight) {
        this.packageHeight = packageHeight;
    }

    public double getPackageWidth() {
        return packageWidth;
    }

    public void setPackageWidth(double packageWidth) {
        this.packageWidth = packageWidth;
    }

    public double getPackageDepth() {
        return packageDepth;
    }

    public void setPackageDepth(double packageDepth) {
        this.packageDepth = packageDepth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderDetailDTO> getShopCart() {
        return shopCart;
    }

    public void setShopCart(List<OrderDetailDTO> shopCart) {
        this.shopCart = shopCart;
    }
}
