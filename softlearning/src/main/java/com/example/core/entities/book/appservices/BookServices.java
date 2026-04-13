package com.example.core.entities.book.appservices;

import org.springframework.stereotype.Service;

import com.example.shared.exceptions.ServiceException;

@Service
public interface BookServices {

    public String getAllBooksToJson() throws ServiceException;

    public String getByIdProductToJson(String id) throws ServiceException;

    public String getByIdProductToXml(String id) throws ServiceException;

    public String addFromJson(String book) throws ServiceException;

    public String addFromXml(String book) throws ServiceException;

    public String updateOneFromJson(String book) throws ServiceException;

    public String updateOneFromXml(String book) throws ServiceException;

    public void deleteByIdProduct(String id) throws ServiceException;
}
