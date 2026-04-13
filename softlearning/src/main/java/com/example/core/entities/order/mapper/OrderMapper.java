package com.example.core.entities.order.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.example.core.entities.order.dto.OrderDTO;
import com.example.core.entities.order.dto.OrderDetailDTO;
import com.example.core.entities.order.model.Order;
import com.example.core.entities.order.model.OrderDetail;
import com.example.core.entities.order.model.OrderStatus;
import com.example.core.entities.shared.validations.Check;
import com.example.shared.exceptions.BuildException;
import com.example.shared.exceptions.GeneralDateTimeException;
import com.example.shared.exceptions.ServiceException;

public class OrderMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss");

    public static OrderDTO OrderToDTO(Order o) throws BuildException {
        List<OrderDetailDTO> details = o.getShopCart().stream()
                .map(d -> new OrderDetailDTO(d.getRef(), d.getPrice(), d.getDiscount(), d.getAmount(), null))
                .collect(Collectors.toList());
        try {
            LocalDateTime paymentDate = o.getPaymentDate() != null ? Check.convertStringToDateTime(o.getPaymentDate(), FORMATTER) : null;
            LocalDateTime deliveryDate = o.getDeliveryDate() != null ? Check.convertStringToDateTime(o.getDeliveryDate(), FORMATTER) : null;
            return new OrderDTO(
                    o.getOrderID(),
                    o.getClientID(),
                    o.getRecieverAddress(),
                    o.getRecieverPerson(),
                    paymentDate,
                    deliveryDate,
                    String.join(",", o.getPhoneContact()),
                    o.getOrderPackage() != null ? o.getOrderPackage().getWeight() : 0,
                    o.getOrderPackage() != null ? o.getOrderPackage().getHeight() : 0,
                    o.getOrderPackage() != null ? o.getOrderPackage().getWidth() : 0,
                    o.getOrderPackage() != null ? o.getOrderPackage().getDepth() : 0,
                    o.getStatus(),
                    details
            );
        } catch (GeneralDateTimeException e) {
            throw new BuildException("Invalid date format: " + e.getMessage());
        }
    }

    public static Order DTOtoOrder(OrderDTO dto) throws BuildException {
        Order o = new Order(dto.getOrderID(), dto.getClientID());
        o.setRecieverAddress(dto.getReceiverAddress());
        o.setRecieverPerson(dto.getReceiverPerson());

        try {

            o.setPaymentDate(Check.convertDateTimeToString(dto.getPaymentDate(), FORMATTER));
            o.setStatus(OrderStatus.FORTHCOMING);
            o.setDeliveryDate(Check.convertDateTimeToString(dto.getDeliveryDate(), FORMATTER));
        } catch (GeneralDateTimeException | ServiceException e) {
            throw new BuildException("Invalid date format: " + e.getMessage());
        }
        o.setPhoneContacts(dto.getPhoneContacts());
        if (dto.getPackageWeight() > 0) {
            o.setDimensions(dto.getPackageWeight() + "," + dto.getPackageHeight() + "," + dto.getPackageWidth() + "," + dto.getPackageDepth());
        }
        o.setStatus(OrderStatus.valueOf(dto.getStatus()));
        for (OrderDetailDTO d : dto.getShopCart()) {
            o.getShopCart().add(new OrderDetail(d.getRef(), d.getPrice(), d.getDiscount(), d.getAmount()));
        }
        return o;
    }
}
