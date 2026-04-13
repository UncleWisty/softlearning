package com.example.infraestructure.persistence.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.core.entities.order.dto.OrderDTO;
import com.example.core.entities.order.persistence.OrderRepository;

@Repository
public interface JpaOrderRepository extends JpaRepository<OrderDTO, Integer>, OrderRepository {

    @Override
    @EntityGraph(attributePaths = {"shopCart"})
    public List<OrderDTO> findAll();

    @Override
    @EntityGraph(attributePaths = {"shopCart"})
    public Optional<OrderDTO> findByOrderID(int orderID);

    @Override
    @EntityGraph(attributePaths = {"shopCart"})
    public List<OrderDTO> findByClientID(int clientID);

    @Override
    public OrderDTO save(OrderDTO order);

    @Override
    public void deleteByOrderID(int orderID);
}