package com.example.core.entities.order.persistence;

import java.util.List;
import java.util.Optional;

import com.example.core.entities.order.dto.OrderDTO;

public interface OrderRepository {

    public List<OrderDTO> findAll();

    public Optional<OrderDTO> findByOrderID(int orderID);

    public List<OrderDTO> findByClientID(int clientID);

    public OrderDTO save(OrderDTO order);

    public void deleteByOrderID(int orderID);
}