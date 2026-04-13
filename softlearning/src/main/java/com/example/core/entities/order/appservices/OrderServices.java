package com.example.core.entities.order.appservices;

import org.springframework.stereotype.Service;

import com.example.shared.exceptions.ServiceException;

@Service
public interface OrderServices {

    public String getAllOrdersToJson() throws ServiceException;

    public String getByOrderIDToJson(int id) throws ServiceException;

    public String getByOrderIDToXml(int id) throws ServiceException;

    public String addFromJson(String order) throws ServiceException;

    public String addFromXml(String order) throws ServiceException;

    public String updateOneFromJson(String order) throws ServiceException;

    public String updateOneFromXml(String order) throws ServiceException;

    public void deleteByOrderID(int id) throws ServiceException;
}