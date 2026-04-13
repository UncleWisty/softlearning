package com.example.core.entities.book.appservices;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.MockedStatic;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.core.entities.book.dto.BookDTO;
import com.example.core.entities.book.mapper.BookMapper;
import static com.example.services.serializers.Serializers.JSON_BOOK;
import static com.example.services.serializers.Serializers.XML_BOOK;
import com.example.services.serializers.SerializersCatalog;
import com.example.shared.exceptions.BuildException;
import com.example.shared.exceptions.ServiceException;

public class BookServicesImplTest extends BookServicesTestBase {

    private BookDTO bookDTO;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        bookDTO = new BookDTO("1234", "Book Name", "This is a valid description with more than ten characters", 10.0, 5, true, "123456789", "Title", "Author", "Publisher", 2020, 1.0, 2.0, 3.0, 4.0);
    }

    @Test
    void testGetAllBooksToJson() throws ServiceException {
        mockedStatic.when(() -> SerializersCatalog.getInstance(JSON_BOOK)).thenReturn(listSerializer);
        List<BookDTO> books = Arrays.asList(bookDTO);
        when(bookRepository.findAll()).thenReturn(books);
        when(listSerializer.serialize(books)).thenReturn("[json]");

        String result = bookServices.getAllBooksToJson();

        assertEquals("[json]", result);
        verify(bookRepository).findAll();
        verify(listSerializer).serialize(books);
    }

    @Test
    void testGetByIdProductToJson() throws ServiceException {
        mockedStatic.when(() -> SerializersCatalog.getInstance(JSON_BOOK)).thenReturn(serializer);
        bookServices.setParentSerializer(serializer);
        when(bookRepository.findByIdProduct("1234")).thenReturn(Optional.of(bookDTO));
        when(serializer.serialize(bookDTO)).thenReturn("{json}");

        String result = bookServices.getByIdProductToJson("1234");

        assertEquals("{json}", result);
        verify(bookRepository).findByIdProduct("1234");
        verify(serializer).serialize(bookDTO);
    }

    @Test
    void testGetByIdProductToJsonNotFound() {
        when(bookRepository.findByIdProduct("1234")).thenReturn(Optional.empty());

        ServiceException exception = assertThrows(ServiceException.class, () -> bookServices.getByIdProductToJson("1234"));
        assertEquals("book 1234 not found", exception.getMessage());
    }

    @Test
    void testGetByIdProductToXml() throws ServiceException {
        mockedStatic.when(() -> SerializersCatalog.getInstance(XML_BOOK)).thenReturn(xmlSerializer);
        bookServices.serializer = xmlSerializer;
        when(bookRepository.findByIdProduct("1234")).thenReturn(Optional.of(bookDTO));
        when(xmlSerializer.serialize(bookDTO)).thenReturn("<xml>");

        String result = bookServices.getByIdProductToXml("1234");

        assertEquals("<xml>", result);
        verify(bookRepository).findByIdProduct("1234");
        verify(xmlSerializer).serialize(bookDTO);
    }

    @Test
    void testGetByIdProductToXmlNotFound() {
        when(bookRepository.findByIdProduct("1234")).thenReturn(Optional.empty());

        ServiceException exception = assertThrows(ServiceException.class, () -> bookServices.getByIdProductToXml("1234"));
        assertEquals("book 1234 not found", exception.getMessage());
    }

    @Test
    void testAddFromJson() throws ServiceException {
        mockedStatic.when(() -> SerializersCatalog.getInstance(JSON_BOOK)).thenReturn(serializer);
        String json = "{json}";
        bookServices.setParentSerializer(serializer);
        when(serializer.deserialize(json, BookDTO.class)).thenReturn(bookDTO);
        when(bookRepository.findByIdProduct("1234")).thenReturn(Optional.empty());
        when(bookRepository.save(bookDTO)).thenReturn(bookDTO);
        when(serializer.serialize(bookDTO)).thenReturn("{saved}");

        String result = bookServices.addFromJson(json);

        assertEquals("{saved}", result);
        verify(bookRepository).findByIdProduct("1234");
        verify(bookRepository).save(bookDTO);
    }

    @Test
    void testAddFromJsonAlreadyExists() throws ServiceException {
        mockedStatic.when(() -> SerializersCatalog.getInstance(JSON_BOOK)).thenReturn(serializer);
        String json = "{json}";
        bookServices.setParentSerializer(serializer);
        when(serializer.deserialize(json, BookDTO.class)).thenReturn(bookDTO);
        when(bookRepository.findByIdProduct("1234")).thenReturn(Optional.of(bookDTO));

        ServiceException exception = assertThrows(ServiceException.class, () -> bookServices.addFromJson(json));
        assertEquals("book 1234 already exists", exception.getMessage());
        verify(bookRepository, never()).save(any());
    }

    @Test
    void testAddFromJsonInvalidData() throws ServiceException {
        mockedStatic.when(() -> SerializersCatalog.getInstance(JSON_BOOK)).thenReturn(serializer);
        String json = "{invalid}";
        bookServices.setParentSerializer(serializer);
        when(serializer.deserialize(json, BookDTO.class)).thenReturn(bookDTO);
        // Mock BookMapper to throw BuildException
        try (MockedStatic<BookMapper> mapperMocked = mockStatic(BookMapper.class)) {
            mapperMocked.when(() -> BookMapper.DTOtoBook(bookDTO)).thenThrow(new BuildException("invalid"));
            ServiceException exception = assertThrows(ServiceException.class, () -> bookServices.addFromJson(json));
            assertEquals("error in the input book data: invalid", exception.getMessage());
        }
    }

    @Test
    void testAddFromXml() throws ServiceException {
        mockedStatic.when(() -> SerializersCatalog.getInstance(XML_BOOK)).thenReturn(xmlSerializer);
        String xml = "<xml>";
        bookServices.setParentSerializer(xmlSerializer);
        when(xmlSerializer.deserialize(xml, BookDTO.class)).thenReturn(bookDTO);
        when(bookRepository.findByIdProduct("1234")).thenReturn(Optional.empty());
        when(bookRepository.save(bookDTO)).thenReturn(bookDTO);
        when(xmlSerializer.serialize(bookDTO)).thenReturn("<saved>");

        String result = bookServices.addFromXml(xml);

        assertEquals("<saved>", result);
        verify(bookRepository).findByIdProduct("1234");
        verify(bookRepository).save(bookDTO);
    }

    @Test
    void testUpdateOneFromJson() throws ServiceException {
        mockedStatic.when(() -> SerializersCatalog.getInstance(JSON_BOOK)).thenReturn(serializer);
        String json = "{json}";
        bookServices.setParentSerializer(serializer);
        when(serializer.deserialize(json, BookDTO.class)).thenReturn(bookDTO);
        when(bookRepository.findByIdProduct("1234")).thenReturn(Optional.of(bookDTO));
        when(bookRepository.save(bookDTO)).thenReturn(bookDTO);
        when(serializer.serialize(bookDTO)).thenReturn("{updated}");

        String result = bookServices.updateOneFromJson(json);

        assertEquals("{updated}", result);
        verify(bookRepository).findByIdProduct("1234");
        verify(bookRepository).save(bookDTO);
    }

    @Test
    void testUpdateOneFromJsonNotFound() throws ServiceException {
        mockedStatic.when(() -> SerializersCatalog.getInstance(JSON_BOOK)).thenReturn(serializer);
        String json = "{json}";
        bookServices.setParentSerializer(serializer);
        when(serializer.deserialize(json, BookDTO.class)).thenReturn(bookDTO);
        when(bookRepository.findByIdProduct("1234")).thenReturn(Optional.empty());

        ServiceException exception = assertThrows(ServiceException.class, () -> bookServices.updateOneFromJson(json));
        assertEquals("book 1234 not found", exception.getMessage());
        verify(bookRepository, never()).save(any());
    }

    @Test
    void testUpdateOneFromXml() throws ServiceException {
        mockedStatic.when(() -> SerializersCatalog.getInstance(XML_BOOK)).thenReturn(xmlSerializer);
        String xml = "<xml>";
        bookServices.setParentSerializer(xmlSerializer);
        when(xmlSerializer.deserialize(xml, BookDTO.class)).thenReturn(bookDTO);
        when(bookRepository.findByIdProduct("1234")).thenReturn(Optional.of(bookDTO));
        when(bookRepository.save(bookDTO)).thenReturn(bookDTO);
        when(xmlSerializer.serialize(bookDTO)).thenReturn("<updated>");

        String result = bookServices.updateOneFromXml(xml);

        assertEquals("<updated>", result);
        verify(bookRepository).findByIdProduct("1234");
        verify(bookRepository).save(bookDTO);
    }

    @Test
    void testDeleteByIdProduct() throws ServiceException {
        when(bookRepository.findByIdProduct("1234")).thenReturn(Optional.of(bookDTO));
        doNothing().when(bookRepository).deleteByIdProduct("1234");

        bookServices.deleteByIdProduct("1234");

        verify(bookRepository).findByIdProduct("1234");
        verify(bookRepository).deleteByIdProduct("1234");
    }

    @Test
    void testDeleteByIdProductNotFound() {
        when(bookRepository.findByIdProduct("1234")).thenReturn(Optional.empty());

        ServiceException exception = assertThrows(ServiceException.class, () -> bookServices.deleteByIdProduct("1234"));
        assertEquals("book 1234 not found", exception.getMessage());
        verify(bookRepository, never()).deleteByIdProduct(anyString());
    }
}
