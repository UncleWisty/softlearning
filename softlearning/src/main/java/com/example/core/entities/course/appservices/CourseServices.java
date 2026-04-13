package com.example.core.entities.course.appservices;

import org.springframework.stereotype.Service;

import com.example.shared.exceptions.ServiceException;

@Service
public interface CourseServices {

    public String getAllCoursesToJson() throws ServiceException;

    public String getByIdProductToJson(String id) throws ServiceException;

    public String addFromJson(String book) throws ServiceException;

    public String updateOneFromJson(String book) throws ServiceException;

    public void deleteByIdProduct(String id) throws ServiceException;
}
