package com.example.core.entities.order.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.example.core.entities.shared.operations.Operation;
import com.example.core.entities.shared.physicals.PhysicalData;
import com.example.core.entities.shared.validations.Check;
import com.example.shared.exceptions.BuildException;
import com.example.shared.exceptions.GeneralDateTimeException;
import com.example.shared.exceptions.ServiceException;

public class Order extends Operation {

    protected int orderID, clientID;
    protected String recieverAddress, recieverPerson;
    protected LocalDateTime paymentDate, deliveryDate;
    protected Set<String> phoneContact;
    public PhysicalData orderPackage = null;
    public ArrayList<OrderDetail> shopCart;
    protected OrderStatus status;

    private Order() {
    }

    public Order(int orderID, int clientID) {
        this.orderID = orderID;
        this.clientID = clientID;
        this.phoneContact = new HashSet<>();
        this.shopCart = new ArrayList<>();
        this.status = OrderStatus.CREATED;
    }

    // *******************ORDER BUILDERS****************************
    public static Order getInstance(int ref, int clientID, String startDate, String description) throws BuildException, GeneralDateTimeException {
        Order o = new Order();
        String errorMessage = "";

        try {
            o.checkData(ref, startDate, description);
        } catch (BuildException | GeneralDateTimeException ex) {
            errorMessage += ex.getMessage() + "; ";
        }

        if (errorMessage.length() > 0) {
            throw new BuildException(errorMessage);
        }
        return o;
    }

    public static Order getInstance(int ref, int clientID, String startDate, String description, String address, String name, String phone, String paymentDate, String deliveryDate, String packageInfo, String shopCartDetails) throws BuildException {
        Order o = new Order();
        String errorMessage = "";

        try {
            o = Order.getInstance(ref, clientID, startDate, description);
        } catch (BuildException | GeneralDateTimeException ex) {
            errorMessage += ex.getMessage() + "; ";
        }

        try {
            o.setRecieverAddress(address);
        } catch (BuildException e) {
            errorMessage += e.getMessage() + "; ";
        }

        try {
            o.setRecieverPerson(name);
        } catch (BuildException e) {
            errorMessage += e.getMessage() + "; ";
        }

        try {
            o.setPhoneContacts(phone);
        } catch (BuildException e) {
            errorMessage += e.getMessage() + "; ";
        }

        try {
            o.setPaymentDate(paymentDate);
        } catch (GeneralDateTimeException ex) {
            errorMessage += ex.getMessage() + "; ";
        }

        try {
            if (o.status == OrderStatus.CONFIRMED) {
                o.status = OrderStatus.FORTHCOMING;
            }
            o.setDeliveryDate(deliveryDate);
        } catch (GeneralDateTimeException | ServiceException e) {
            errorMessage += e.getMessage() + "; ";
        }

        if (packageInfo != null) {
            try {
                o.setDimensions(packageInfo);
            } catch (BuildException e) {
                errorMessage += e.getMessage() + "; ";
            }
        }

        if (shopCartDetails != null) {
            try {
                o.setShopCartDetails(shopCartDetails);
            } catch (BuildException e) {
                errorMessage += e.getMessage() + "; ";
            }
        }

        if (errorMessage.length() > 0) {
            throw new BuildException(errorMessage);
        }

        return o;
    }
    //****************PACKAGE ADMIN***********************

    public void setDimensions(String csvDimensions) throws BuildException {
        String[] parts = csvDimensions.split(",");

        if (parts.length != 4) {
            throw new BuildException("csv does not contain 4 parts");
        }

        try {
            double weight = Double.parseDouble(parts[0].trim());
            double height = Double.parseDouble(parts[1].trim());
            double width = Double.parseDouble(parts[2].trim());
            double depth = Double.parseDouble(parts[3].trim());

            PhysicalData pd = PhysicalData.getInstance(weight, height, width, depth);
            this.orderPackage = pd;
        } catch (BuildException e) {
            throw new BuildException("bad dimension format: " + e.getMessage());
        }
    }

    public int setWeight(double weight) throws BuildException {
        if (this.orderPackage == null) {
            return -1;
        }
        this.orderPackage.setWeight(weight);
        return 0;
    }

    public int setHeight(double height) throws BuildException {
        if (this.orderPackage == null) {
            return -1;
        }
        this.orderPackage.setHeight(height);
        return 0;
    }

    public int setWidth(double width) throws BuildException {
        if (this.orderPackage == null) {
            return -1;
        }
        this.orderPackage.setWidth(width);
        return 0;
    }

    public int setDepth(double depth) throws BuildException {
        if (this.orderPackage == null) {
            return -1;
        }
        this.orderPackage.setDepth(depth);
        return 0;
    }

    // ************SHOP CART ADMIN*************
    public int addDetail(String id, double price, double discount, int amount) {

        this.shopCart.add(new OrderDetail(id, price, discount, amount));
        return 0;
    }

    public String getDetailNice(int pos) {
        return "Referencia: " + this.shopCart.get(pos).getRef()
                + " Precio: " + this.shopCart.get(pos).getPrice()
                + " Descuento: " + this.shopCart.get(pos).getDiscount()
                + " Cantidad: " + this.shopCart.get(pos).getAmount();
    }

    public String getDetail(int pos) {
        return this.shopCart.get(pos).getRef()
                + "," + this.shopCart.get(pos).getPrice()
                + "," + this.shopCart.get(pos).getDiscount()
                + "," + this.shopCart.get(pos).getAmount();
    }

    public String getDetails() {
        String details = "";
        for (int pos = 0; pos < this.numDetails(); pos++) {
            details += this.getDetail(pos) + ";";
        }
        return details;
    }

    public int numDetails() {
        return this.shopCart.size();
    }

    public void setShopCartDetails(String shopCartDetails) throws BuildException {
        if (shopCartDetails == null || shopCartDetails.trim().isEmpty()) {
            throw new BuildException("Shop cart details are empty");
        }

        String[] detailLines = shopCartDetails.split(";");
        for (String line : detailLines) {
            if (line == null) {
                continue;
            }
            String trimmed = line.trim();
            if (trimmed.isEmpty()) {
                continue;
            }

            String[] parts = trimmed.split(",");
            if (parts.length != 4) {
                throw new BuildException("Invalid shop cart detail format: " + line);
            }

            String id = parts[0].trim();
            double price;
            double discount;
            int amount;
            try {
                price = Double.parseDouble(parts[1].trim());
                discount = Double.parseDouble(parts[2].trim());
                amount = Integer.parseInt(parts[3].trim());
            } catch (NumberFormatException ex) {
                throw new BuildException("Invalid number in shop cart detail: " + line);
            }

            this.shopCart.add(new OrderDetail(id, price, discount, amount));
        }
    }

    public ArrayList<OrderDetail> getShopCart() {
        return shopCart;
    }

    public ArrayList<OrderDetail> getShopCartList() {
        return new ArrayList<>(this.shopCart);
    }

    public PhysicalData getOrderPackage() {
        return orderPackage;
    }

    public int updateShopCartDetails(String ref, int newAmount) {

        if (ref == null || ref.trim().isEmpty()) {
            return -1;
        }

        if (newAmount < 0) {
            return -1;
        }

        for (OrderDetail detail : this.shopCart) {
            if (detail.getRef().equals(ref.trim())) {

                try {
                    detail.setAmount(newAmount);
                    return 0;
                } catch (BuildException e) {
                    return -1;
                }
            }
        }

        return -1;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (OrderDetail detail : this.shopCart) {
            totalPrice += detail.getDetailCost();
        }
        return totalPrice;
    }

    public String getPackageDimensionsCsv() {
        if (this.orderPackage == null) {
            return null;
        }
        return this.orderPackage.getWeight() + "," + this.orderPackage.getHeight() + "," + this.orderPackage.getWidth() + "," + this.orderPackage.getDepth();
    }

    //********************STATUS MANAGEMENT **************************
    public String getStatus() {
        return status.toString();
    }

    public void cancelOrder() throws ServiceException {
        if (this.status == OrderStatus.DELIVERED || this.status == OrderStatus.FINISHED) {
            throw new ServiceException("Cannot cancel a delivered or finished order.");
        }
        this.status = OrderStatus.CANCELLED;
    }

    public void setStatus(OrderStatus status) throws BuildException {
        if (this.status == OrderStatus.FINISHED) {
            throw new BuildException("Cannot change status of a finished order.");
        }
        this.status = status;
    }

    @Override
    public void setFinishDate(String finishDate) throws GeneralDateTimeException {
        if (this.status != OrderStatus.DELIVERED) {
            throw new GeneralDateTimeException("Order status differs to FORTHCOMING");
        }
        super.setFinishDate(finishDate);
        this.status = OrderStatus.FINISHED;
    }

    //******************PHONE MANAGEMENT***************
    public int addPhoneContact(String phone) {
        if (phone.trim().isEmpty()) {
            return -1;
        }
        this.phoneContact.add(phone.trim());
        return 0;
    }

    public String getPhoneContact() {
        return String.join(",", new HashSet<>(this.phoneContact));
    }

    public Set<String> getPhoneContacts() {
        return new HashSet<>(this.phoneContact);
    }

    public void setPhoneContacts(String phones) throws BuildException {

        if (phones == null || phones.trim().isEmpty()) {
            throw new BuildException("phone list cannot be empty");
        }

        try {
            String[] phoneArray = phones.split(",");

            for (String phone : phoneArray) {
                this.addPhoneContact(phone);
            }
        } catch (Exception e) {
            throw new BuildException("error formatting phones: " + phones + " ||  original error: " + e.getMessage());
        }
    }

    // *******BASIC GETTERS AND SETTERS*************
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) throws BuildException {
        if (this.status != OrderStatus.CREATED) {
            throw new BuildException("Cannot modify Order ID. Order status is: " + this.status.toString());
        }
        if (orderID < 0) {
            throw new BuildException("order id must not be 0");
        }
        this.orderID = orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) throws BuildException {
        if (this.status != OrderStatus.CREATED) {
            throw new BuildException("Cannot modify Client ID. Order status is: " + this.status.toString());
        }
        if (clientID < 0) {
            throw new BuildException("client id must not be 0");
        }

        this.clientID = clientID;
    }

    public String getRecieverAddress() {
        return recieverAddress;
    }

    public void setRecieverAddress(String recieverAddress) throws BuildException {
        if (this.status != OrderStatus.CREATED) {
            throw new BuildException("Cannot modify Reciever Address. Order status is: " + this.status.toString());
        }
        if (recieverAddress == null || recieverAddress.trim().isEmpty()) {
            throw new BuildException("Reciever address cannot be empty.");
        }
        this.recieverAddress = recieverAddress;
    }

    public String getRecieverPerson() {
        return recieverPerson;
    }

    public void setRecieverPerson(String recieverPerson) throws BuildException {
        if (this.status != OrderStatus.CREATED) {
            throw new BuildException("Cannot modify Reciever Person. Order status is: " + this.status.toString());
        }
        if (recieverPerson == null || recieverPerson.trim().isEmpty()) {
            throw new BuildException("Reciever person cannot be empty.");
        }
        this.recieverPerson = recieverPerson;
    }

    public String getPaymentDate() {
        if (this.paymentDate == null) {
            return null;
        }
        return this.paymentDate.format(this.formatter);
    }

    public void setPaymentDate(String paymentDate) throws GeneralDateTimeException {
        if (this.status != OrderStatus.CREATED) {
            throw new GeneralDateTimeException("order status differs to CREATED");
        }

        if (paymentDate == null) {
            throw new GeneralDateTimeException("Bad payment date");
        }

        this.paymentDate = Check.convertStringToDateTime(paymentDate, this.formatter);
        this.status = OrderStatus.CONFIRMED;
    }

    public String getDeliveryDate() {
        if (this.deliveryDate == null) {
            return null;
        }
        return this.deliveryDate.format(this.formatter);
    }

    public void setDeliveryDate(String deliveryDate) throws GeneralDateTimeException, ServiceException {
        if (this.status != OrderStatus.FORTHCOMING) {
            throw new ServiceException("order status differs to FORTHCOMING");
        }

        if (deliveryDate == null) {
            throw new GeneralDateTimeException("Bad delivery date");
        }
        this.deliveryDate = Check.convertStringToDateTime(deliveryDate, this.formatter);
        this.status = OrderStatus.DELIVERED;
    }

    // Devuelve startDate en formato String o null si no está establecido
    public String getStartDateSafe() {
        try {
            return super.getStartDate();
        } catch (GeneralDateTimeException e) {
            return null;
        }
    }

}
