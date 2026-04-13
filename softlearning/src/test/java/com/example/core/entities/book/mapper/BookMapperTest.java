package com.example.core.entities.book.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.example.core.entities.book.dto.BookDTO;
import com.example.core.entities.book.model.Book;
import com.example.shared.exceptions.BuildException;

public class BookMapperTest {

    @Test
    void testBookToDTO() throws BuildException {
        Book book = Book.getInstance("BOOK001", "Book Name", "Description", 29.99, 10, true, "978-3-16-148410-0", "Book Title", "Author Name", "Publisher", 2020, 1.5, 20.0, 15.0, 2.0);
        BookDTO dto = BookMapper.BookToDTO(book);
        assertEquals("BOOK001", dto.getIdProduct());
        assertEquals("Book Name", dto.getName());
        assertEquals("Description", dto.getDescription());
        assertEquals(29.99, dto.getPrice());
        assertEquals(10, dto.getStock());
        assertEquals(true, dto.isAvailable());
        assertEquals("978-3-16-148410-0", dto.getIsbn());
        assertEquals("Book Title", dto.getTitle());
        assertEquals("Author Name", dto.getAuthor());
        assertEquals("Publisher", dto.getPublisher());
        assertEquals(2020, dto.getPublishYear());
        assertEquals(1.5, dto.getWeight());
        assertEquals(20.0, dto.getHeight());
        assertEquals(15.0, dto.getWidth());
        assertEquals(2.0, dto.getDepth());
    }

    @Test
    void testDTOtoBookValid() throws BuildException {
        BookDTO dto = new BookDTO("BOOK001", "Book Name", "Description", 29.99, 10, true, "978-3-16-148410-0", "Book Title", "Author Name", "Publisher", 2020, 1.5, 20.0, 15.0, 2.0);
        Book book = BookMapper.DTOtoBook(dto);
        assertEquals("BOOK001", book.getIdProduct());
        assertEquals("Book Name", book.getName());
        assertEquals("Description", book.getDescription());
        assertEquals(29.99, book.getPrice());
        assertEquals(10, book.getStock());
        assertEquals(true, book.isAvailable());
        assertEquals("978-3-16-148410-0", book.getIsbn());
        assertEquals("Book Title", book.getTitle());
        assertEquals("Author Name", book.getAuthor());
        assertEquals("Publisher", book.getPublisher());
        assertEquals(2020, book.getPublishYear());
        assertEquals(1.5, book.getPhysicalData().getWeight());
        assertEquals(20.0, book.getPhysicalData().getHeight());
        assertEquals(15.0, book.getPhysicalData().getWidth());
        assertEquals(2.0, book.getPhysicalData().getDepth());
    }

    @Test
    void testDTOtoBookInvalid() {
        BookDTO dto = new BookDTO("BOO", "Bo", "Desc", -1.0, -1, true, null, "", "", "P", -1, 0, 20.0, 15.0, 2.0);
        assertThrows(BuildException.class, () -> BookMapper.DTOtoBook(dto));
    }
}
