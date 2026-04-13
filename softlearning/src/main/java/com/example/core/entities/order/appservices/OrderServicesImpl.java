package com.example.core.entities.order.appservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.example.core.entities.order.dto.OrderDTO;
import com.example.core.entities.order.mapper.OrderMapper;
import com.example.core.entities.order.persistence.OrderRepository;
import com.example.services.serializers.Serializer;
import com.example.services.serializers.Serializers;
import com.example.services.serializers.SerializersCatalog;
import com.example.shared.exceptions.BuildException;
import com.example.shared.exceptions.ServiceException;

@Controller
public class OrderServicesImpl implements OrderServices {

    @Autowired
    private OrderRepository orderRepository;
    private Serializer<OrderDTO> serializer;

    protected OrderDTO getDTO(int id) {
        return orderRepository.findByOrderID(id).orElse(null);
    }

    protected OrderDTO getByOrderID(int id) throws ServiceException {
        OrderDTO order = this.getDTO(id);

        if (order == null) {
            throw new ServiceException("order " + id + " not found");
        }
        return order;
    }

    protected OrderDTO checkInputData(String order) throws ServiceException {
        try {
            OrderDTO orderDTO = this.serializer.deserialize(order, OrderDTO.class);
            OrderMapper.DTOtoOrder(orderDTO);
            if (orderDTO.getShopCart() != null) {
                orderDTO.setShopCart(orderDTO.getShopCart());
            }
            return orderDTO;
        } catch (BuildException e) {
            throw new ServiceException("error in the input order data: " + e.getMessage());
        }
    }

    @Override
    public String getAllOrdersToJson() throws ServiceException {
        Serializer<List<OrderDTO>> listSerializer = (Serializer<List<OrderDTO>>) SerializersCatalog.getInstance(Serializers.JSON_ORDER);
        return listSerializer.serialize(orderRepository.findAll());
    }

    protected OrderDTO getById(int id) throws ServiceException {
        return this.getByOrderID(id);
    }

    protected OrderDTO newOrder(String order) throws ServiceException {
        OrderDTO orderDTO = this.checkInputData(order);

        if (this.getDTO(orderDTO.getOrderID()) == null) {
            return orderRepository.save(orderDTO);
        }
        throw new ServiceException("order " + orderDTO.getOrderID() + " already exists");
    }

    protected OrderDTO updateOrder(String order) throws ServiceException {
        OrderDTO orderDTO = this.checkInputData(order);
        this.getByOrderID(orderDTO.getOrderID());
        return orderRepository.save(orderDTO);
    }

    @Override
    public String getByOrderIDToJson(int id) throws ServiceException {
        this.serializer = (Serializer<OrderDTO>) SerializersCatalog.getInstance(Serializers.JSON_ORDER);
        return serializer.serialize(this.getById(id));
    }

    @Override
    public String getByOrderIDToXml(int id) throws ServiceException {
        this.serializer = (Serializer<OrderDTO>) SerializersCatalog.getInstance(Serializers.XML_ORDER);
        return serializer.serialize(this.getById(id));
    }

    @Override
    public String addFromJson(String order) throws ServiceException {
        this.serializer = (Serializer<OrderDTO>) SerializersCatalog.getInstance(Serializers.JSON_ORDER);
        return serializer.serialize(this.newOrder(order));
    }

    @Override
    public String addFromXml(String order) throws ServiceException {
        this.serializer = (Serializer<OrderDTO>) SerializersCatalog.getInstance(Serializers.XML_ORDER);
        return serializer.serialize(this.newOrder(order));
    }

    @Override
    public String updateOneFromJson(String order) throws ServiceException {
        this.serializer = (Serializer<OrderDTO>) SerializersCatalog.getInstance(Serializers.JSON_ORDER);
        return serializer.serialize(this.updateOrder(order));
    }

    @Override
    public String updateOneFromXml(String order) throws ServiceException {
        this.serializer = (Serializer<OrderDTO>) SerializersCatalog.getInstance(Serializers.XML_ORDER);
        return serializer.serialize(this.updateOrder(order));
    }

    @Override
    @Transactional
    public void deleteByOrderID(int id) throws ServiceException {
        this.getByOrderID(id);
        orderRepository.deleteByOrderID(id);
    }
}