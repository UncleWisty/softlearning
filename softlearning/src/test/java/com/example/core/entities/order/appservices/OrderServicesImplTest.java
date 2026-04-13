package com.example.core.entities.order.appservices;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.core.entities.order.dto.OrderDTO;
import com.example.core.entities.order.dto.OrderDetailDTO;
import static com.example.services.serializers.Serializers.JSON_ORDER;
import static com.example.services.serializers.Serializers.XML_ORDER;
import com.example.shared.exceptions.ServiceException;

public class OrderServicesImplTest extends OrderServicesTestBase {

    private OrderDTO orderDTO;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        orderDTO = new OrderDTO(
                1001,
                1001,
                500,
                "Pedido de prueba con detalles suficientes",
                "01/01/2026-10:00:00",
                null,
                "Calle Mayor 1",
                "Ada Lovelace",
                null,
                null,
                "600111222,600333444",
                1.0,
                2.0,
                3.0,
                4.0,
                "CREATED",
                Arrays.asList(new OrderDetailDTO("BK001", 20.0, 2.0, 2, null))
        );
    }

    @Test
    void testGetAllOrdersToJson() throws ServiceException {
        mockedStatic.when(() -> com.example.services.serializers.SerializersCatalog.getInstance(JSON_ORDER)).thenReturn(listSerializer);
        when(orderRepository.findAll()).thenReturn(Arrays.asList(orderDTO));
        when(listSerializer.serialize(Arrays.asList(orderDTO))).thenReturn("[json]");

        String result = orderServices.getAllOrdersToJson();

        assertEquals("[json]", result);
        verify(orderRepository).findAll();
        verify(listSerializer).serialize(Arrays.asList(orderDTO));
    }

    @Test
    void testGetByOrderIDToJson() throws ServiceException {
        mockedStatic.when(() -> com.example.services.serializers.SerializersCatalog.getInstance(JSON_ORDER)).thenReturn(serializer);
        orderServices.setParentSerializer(serializer);
        when(orderRepository.findByOrderID(1001)).thenReturn(Optional.of(orderDTO));
        when(serializer.serialize(orderDTO)).thenReturn("{json}");

        String result = orderServices.getByOrderIDToJson(1001);

        assertEquals("{json}", result);
        verify(orderRepository).findByOrderID(1001);
        verify(serializer).serialize(orderDTO);
    }

    @Test
    void testGetByOrderIDToXml() throws ServiceException {
        mockedStatic.when(() -> com.example.services.serializers.SerializersCatalog.getInstance(XML_ORDER)).thenReturn(xmlSerializer);
        orderServices.setParentSerializer(xmlSerializer);
        when(orderRepository.findByOrderID(1001)).thenReturn(Optional.of(orderDTO));
        when(xmlSerializer.serialize(orderDTO)).thenReturn("<xml/>");

        String result = orderServices.getByOrderIDToXml(1001);

        assertEquals("<xml/>", result);
        verify(orderRepository).findByOrderID(1001);
        verify(xmlSerializer).serialize(orderDTO);
    }

    @Test
    void testAddFromJson() throws ServiceException {
        mockedStatic.when(() -> com.example.services.serializers.SerializersCatalog.getInstance(JSON_ORDER)).thenReturn(serializer);
        orderServices.setParentSerializer(serializer);
        when(serializer.deserialize("{json}", OrderDTO.class)).thenReturn(orderDTO);
        when(orderRepository.findByOrderID(1001)).thenReturn(Optional.empty());
        when(orderRepository.save(orderDTO)).thenReturn(orderDTO);
        when(serializer.serialize(orderDTO)).thenReturn("{saved}");

        String result = orderServices.addFromJson("{json}");

        assertEquals("{saved}", result);
        verify(orderRepository).save(orderDTO);
    }

    @Test
    void testAddFromJsonAlreadyExists() throws ServiceException {
        mockedStatic.when(() -> com.example.services.serializers.SerializersCatalog.getInstance(JSON_ORDER)).thenReturn(serializer);
        orderServices.setParentSerializer(serializer);
        when(serializer.deserialize("{json}", OrderDTO.class)).thenReturn(orderDTO);
        when(orderRepository.findByOrderID(1001)).thenReturn(Optional.of(orderDTO));

        ServiceException exception = assertThrows(ServiceException.class, () -> orderServices.addFromJson("{json}"));

        assertEquals("order 1001 already exists", exception.getMessage());
        verify(orderRepository, never()).save(any());
    }

    @Test
    void testUpdateOneFromJson() throws ServiceException {
        mockedStatic.when(() -> com.example.services.serializers.SerializersCatalog.getInstance(JSON_ORDER)).thenReturn(serializer);
        orderServices.setParentSerializer(serializer);
        when(serializer.deserialize("{json}", OrderDTO.class)).thenReturn(orderDTO);
        when(orderRepository.findByOrderID(1001)).thenReturn(Optional.of(orderDTO));
        when(orderRepository.save(orderDTO)).thenReturn(orderDTO);
        when(serializer.serialize(orderDTO)).thenReturn("{updated}");

        String result = orderServices.updateOneFromJson("{json}");

        assertEquals("{updated}", result);
        verify(orderRepository).save(orderDTO);
    }

    @Test
    void testDeleteByOrderID() throws ServiceException {
        when(orderRepository.findByOrderID(1001)).thenReturn(Optional.of(orderDTO));
        doNothing().when(orderRepository).deleteByOrderID(1001);

        orderServices.deleteByOrderID(1001);

        verify(orderRepository).deleteByOrderID(1001);
    }

    @Test
    void testDeleteByOrderIDNotFound() {
        when(orderRepository.findByOrderID(1001)).thenReturn(Optional.empty());

        ServiceException exception = assertThrows(ServiceException.class, () -> orderServices.deleteByOrderID(1001));

        assertEquals("order 1001 not found", exception.getMessage());
        verify(orderRepository, never()).deleteByOrderID(anyInt());
    }
}