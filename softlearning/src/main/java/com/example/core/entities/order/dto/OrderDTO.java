package com.example.core.entities.order.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderDTO {

    @Id
    @Column(name = "order_id")
    private int orderID;
    @Column(name = "operation_ref")
    private int operationRef;
    @Column(name = "client_id")
    private int clientID;
    @Column(name = "description")
    private String description;
    @Column(name = "start_date")
    private String startDate;
    @Column(name = "finish_date")
    private String finishDate;
    @Column(name = "receiver_address")
    private String receiverAddress;
    @Column(name = "receiver_person")
    private String receiverPerson;
    @Column(name = "payment_date")
    private String paymentDate;
    @Column(name = "delivery_date")
    private String deliveryDate;
    @Column(name = "phone_contacts")
    private String phoneContacts;
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
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OrderDetailDTO> shopCart = new ArrayList<>();

    public OrderDTO() {
    }

    public OrderDTO(int orderID, int operationRef, int clientID, String description, String startDate,
            String finishDate, String receiverAddress, String receiverPerson, String paymentDate,
            String deliveryDate, String phoneContacts, double packageWeight, double packageHeight,
            double packageWidth, double packageDepth, String status, List<OrderDetailDTO> shopCart) {
        this.orderID = orderID;
        this.operationRef = operationRef;
        this.clientID = clientID;
        this.description = description;
        this.startDate = startDate;
        this.finishDate = finishDate;
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
        this.setShopCart(shopCart);
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getOperationRef() {
        return operationRef;
    }

    public void setOperationRef(int operationRef) {
        this.operationRef = operationRef;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
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

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
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
        this.shopCart = new ArrayList<>();
        if (shopCart == null) {
            return;
        }

        for (OrderDetailDTO detail : shopCart) {
            detail.setOrder(this);
            this.shopCart.add(detail);
        }
    }
}
