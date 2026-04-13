package com.example.services.serializers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.shared.exceptions.ServiceException;

public class JacksonSerializer<T> implements Serializer<T> {

    private ObjectMapper mapper = new ObjectMapper();

    public JacksonSerializer() {
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    @Override
    public String serialize(T object) throws ServiceException {
        try {
            return this.mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public T deserialize(String source, Class<T> object) throws ServiceException {
        try {
            return mapper.readValue(source, object);
        } catch (JsonProcessingException e) {
            throw new ServiceException(e.getMessage());
        }
    }

}
