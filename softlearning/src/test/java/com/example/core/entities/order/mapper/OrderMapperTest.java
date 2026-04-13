package com.example.core.entities.order.mapper;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import com.example.core.entities.order.dto.OrderDTO;
import com.example.core.entities.order.dto.OrderDetailDTO;
import com.example.core.entities.order.model.Order;
import com.example.core.entities.order.model.OrderStatus;
import com.example.shared.exceptions.BuildException;

public class OrderMapperTest {

    @Test
    void testOrderToDTO() throws BuildException {
        Order order = new Order(1, 100);
        order.addDetail("ref1", 10.0, 2.0, 5);
        order.setDimensions("1.0,2.0,3.0,4.0");

        OrderDTO dto = OrderMapper.OrderToDTO(order);

        assertNotNull(dto);
        assertEquals(1, dto.getOrderID());
        assertEquals(100, dto.getClientID());
        assertEquals(1.0, dto.getPackageWeight());
        assertEquals(OrderStatus.CREATED.name(), dto.getStatus());
        assertEquals(1, dto.getShopCart().size());
        assertEquals("ref1", dto.getShopCart().get(0).getRef());
    }

    @Test
    void testDTOtoOrder() throws BuildException {
        OrderDetailDTO detailDTO = new OrderDetailDTO("ref1", 10.0, 2.0, 5, null);
        OrderDTO dto = new OrderDTO(1, 1, 100, "Pedido de prueba con descripcion valida", "01/01/2026-10:00:00", null,
                "123 Main St", "John Doe", null, null, "123-456-7890", 1.0, 2.0, 3.0, 4.0,
                OrderStatus.CREATED.name(), Arrays.asList(detailDTO));

        Order order = OrderMapper.DTOtoOrder(dto);

        assertNotNull(order);
        assertEquals(1, order.numDetails());
        assertEquals("ref1,10.0,2.0,5", order.getDetail(0));
        assertEquals("Pedido de prueba con descripcion valida", order.getDescription());
    }
}
