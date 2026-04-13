package com.example.services.serializers;

import com.example.shared.exceptions.ServiceException;

public interface Serializer<T> {

    public String serialize(T object) throws ServiceException;

    public T deserialize(String source, Class<T> object) throws ServiceException;
}
