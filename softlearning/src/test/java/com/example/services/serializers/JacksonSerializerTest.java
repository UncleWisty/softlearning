package com.example.services.serializers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.example.core.entities.book.dto.BookDTO;
import com.example.shared.exceptions.ServiceException;

public class JacksonSerializerTest {

    private JacksonSerializer<BookDTO> serializer = new JacksonSerializer<>();

    @Test
    void testSerialize() throws ServiceException {
        BookDTO book = new BookDTO("1", "Book", "Desc", 10.0, 5, true, "123", "Title", "Author", "Publisher", 2020, 1.0, 2.0, 3.0, 4.0);
        String json = serializer.serialize(book);
        assertEquals(true, json.contains("\"idProduct\":\"1\""));
        assertEquals(true, json.contains("\"name\":\"Book\""));
    }

    @Test
    void testDeserialize() throws ServiceException {
        String json = "{\"idProduct\":\"1\",\"name\":\"Book\",\"description\":\"Desc\",\"price\":10.0,\"stock\":5,\"isAvailable\":true,\"isbn\":\"123\",\"title\":\"Title\",\"author\":\"Author\",\"publisher\":\"Publisher\",\"publishYear\":2020,\"weight\":1.0,\"height\":2.0,\"width\":3.0,\"depth\":4.0}";
        BookDTO book = serializer.deserialize(json, BookDTO.class);
        assertEquals("1", book.getIdProduct());
        assertEquals("Book", book.getName());
    }

    @Test
    void testDeserializeInvalid() {
        String invalidJson = "{invalid}";
        assertThrows(ServiceException.class, () -> serializer.deserialize(invalidJson, BookDTO.class));
    }
}
