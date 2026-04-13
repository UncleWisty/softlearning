package com.example.services.serializers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.example.core.entities.book.dto.BookDTO;
import com.example.shared.exceptions.ServiceException;

public class XmlJacksonSerializerTest {

    private XmlJacksonSerializer<BookDTO> serializer = new XmlJacksonSerializer<>();

    @Test
    void testSerialize() throws ServiceException {
        BookDTO book = new BookDTO("1", "Book", "Desc", 10.0, 5, true, "123", "Title", "Author", "Publisher", 2020, 1.0, 2.0, 3.0, 4.0);
        String xml = serializer.serialize(book);
        assertEquals(true, xml.contains("<idProduct>1</idProduct>"));
        assertEquals(true, xml.contains("<name>Book</name>"));
    }

    @Test
    void testDeserialize() throws ServiceException {
        String xml = "<BookDTO><idProduct>1</idProduct><name>Book</name><description>Desc</description><price>10.0</price><stock>5</stock><isAvailable>true</isAvailable><isbn>123</isbn><title>Title</title><author>Author</author><publisher>Publisher</publisher><publishYear>2020</publishYear><weight>1.0</weight><height>2.0</height><width>3.0</width><depth>4.0</depth></BookDTO>";
        BookDTO book = serializer.deserialize(xml, BookDTO.class);
        assertEquals("1", book.getIdProduct());
        assertEquals("Book", book.getName());
    }

    @Test
    void testDeserializeInvalid() {
        String invalidXml = "<invalid>";
        assertThrows(ServiceException.class, () -> serializer.deserialize(invalidXml, BookDTO.class));
    }
}
