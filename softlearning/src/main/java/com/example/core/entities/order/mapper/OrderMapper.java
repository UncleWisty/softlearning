package com.example.core.entities.order.mapper;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    public static OrderDTO OrderToDTO(Order order) throws BuildException {
        List<OrderDetailDTO> details = new ArrayList<>();
        String finishDate = null;

        try {
            finishDate = order.getFinishDate() != null ? Check.convertDateTimeToString(order.getFinishDate(), FORMATTER) : null;
        } catch (GeneralDateTimeException e) {
            throw new BuildException("Invalid finish date: " + e.getMessage());
        }

        OrderDTO dto = new OrderDTO(
                order.getOrderID(),
                order.getRef() > 0 ? order.getRef() : order.getOrderID(),
                order.getClientID(),
                order.getDescription(),
                order.getStartDateSafe(),
                finishDate,
                order.getRecieverAddress(),
                order.getRecieverPerson(),
                order.getPaymentDate(),
                order.getDeliveryDate(),
                order.getPhoneContact(),
                order.getOrderPackage() != null ? order.getOrderPackage().getWeight() : 0,
                order.getOrderPackage() != null ? order.getOrderPackage().getHeight() : 0,
                order.getOrderPackage() != null ? order.getOrderPackage().getWidth() : 0,
                order.getOrderPackage() != null ? order.getOrderPackage().getDepth() : 0,
                order.getStatus(),
                details
        );

        for (OrderDetail detail : order.getShopCart()) {
            details.add(new OrderDetailDTO(detail.getRef(), detail.getPrice(), detail.getDiscount(), detail.getAmount(), dto));
        }

        dto.setShopCart(details);
        return dto;
    }

    public static Order DTOtoOrder(OrderDTO dto) throws BuildException {
        Order order = new Order(dto.getOrderID(), dto.getClientID());

        if (dto.getOperationRef() > 0) {
            order.setRef(dto.getOperationRef());
        } else {
            order.setRef(dto.getOrderID());
        }
        order.setDescription(dto.getDescription());

        try {
            if (hasText(dto.getStartDate())) {
                order.setStartDate(dto.getStartDate());
            }
            if (hasText(dto.getReceiverAddress())) {
                order.setRecieverAddress(dto.getReceiverAddress());
            }
            if (hasText(dto.getReceiverPerson())) {
                order.setRecieverPerson(dto.getReceiverPerson());
            }
            if (hasText(dto.getPhoneContacts())) {
                order.setPhoneContacts(dto.getPhoneContacts());
            }
            if (dto.getPackageWeight() > 0) {
                order.setDimensions(dto.getPackageWeight() + "," + dto.getPackageHeight() + "," + dto.getPackageWidth() + "," + dto.getPackageDepth());
            }
            if (hasText(dto.getPaymentDate())) {
                order.setPaymentDate(dto.getPaymentDate());
            }
            if (hasText(dto.getDeliveryDate())) {
                if (OrderStatus.CONFIRMED.name().equals(order.getStatus())) {
                    order.setStatus(OrderStatus.FORTHCOMING);
                }
                order.setDeliveryDate(dto.getDeliveryDate());
            }
            if (hasText(dto.getFinishDate())) {
                order.setFinishDate(dto.getFinishDate());
            }
            if (hasText(dto.getStatus()) && !dto.getStatus().equals(order.getStatus())) {
                order.setStatus(OrderStatus.valueOf(dto.getStatus()));
            }
        } catch (GeneralDateTimeException | ServiceException e) {
            throw new BuildException("Invalid date format: " + e.getMessage());
        }

        if (dto.getShopCart() != null) {
            for (OrderDetailDTO detail : dto.getShopCart()) {
                order.getShopCart().add(new OrderDetail(detail.getRef(), detail.getPrice(), detail.getDiscount(), detail.getAmount()));
            }
        }

        return order;
    }

    private static boolean hasText(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
