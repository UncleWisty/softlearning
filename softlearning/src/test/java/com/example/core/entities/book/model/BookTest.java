package com.example.core.entities.book.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.example.shared.exceptions.BuildException;

public class BookTest {

    @Test
    void testGetInstanceValid() throws BuildException {
        Book book = Book.getInstance("BOOK001", "Book Name", "Description", 29.99, 10, true, "978-3-16-148410-0", "Book Title", "Author Name", "Publisher", 2020, 1.5, 20.0, 15.0, 2.0);
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
    void testGetInstanceInvalidIdProduct() {
        assertThrows(BuildException.class, () -> Book.getInstance("BOO", "Book Name", "Description", 29.99, 10, true, "978-3-16-148410-0", "Book Title", "Author Name", "Publisher", 2020, 1.5, 20.0, 15.0, 2.0));
    }

    @Test
    void testGetInstanceInvalidName() {
        assertThrows(BuildException.class, () -> Book.getInstance("BOOK001", "Bo", "Description", 29.99, 10, true, "978-3-16-148410-0", "Book Title", "Author Name", "Publisher", 2020, 1.5, 20.0, 15.0, 2.0));
    }

    @Test
    void testGetInstanceInvalidDescription() {
        assertThrows(BuildException.class, () -> Book.getInstance("BOOK001", "Book Name", "Desc", 29.99, 10, true, "978-3-16-148410-0", "Book Title", "Author Name", "Publisher", 2020, 1.5, 20.0, 15.0, 2.0));
    }

    @Test
    void testGetInstanceInvalidPrice() {
        assertThrows(BuildException.class, () -> Book.getInstance("BOOK001", "Book Name", "Description", -1.0, 10, true, "978-3-16-148410-0", "Book Title", "Author Name", "Publisher", 2020, 1.5, 20.0, 15.0, 2.0));
    }

    @Test
    void testGetInstanceInvalidStock() {
        assertThrows(BuildException.class, () -> Book.getInstance("BOOK001", "Book Name", "Description", 29.99, -1, true, "978-3-16-148410-0", "Book Title", "Author Name", "Publisher", 2020, 1.5, 20.0, 15.0, 2.0));
    }

    @Test
    void testGetInstanceInvalidIsbn() {
        assertThrows(BuildException.class, () -> Book.getInstance("BOOK001", "Book Name", "Description", 29.99, 10, true, null, "Book Title", "Author Name", "Publisher", 2020, 1.5, 20.0, 15.0, 2.0));
    }

    @Test
    void testGetInstanceInvalidTitle() {
        assertThrows(BuildException.class, () -> Book.getInstance("BOOK001", "Book Name", "Description", 29.99, 10, true, "978-3-16-148410-0", "", "Author Name", "Publisher", 2020, 1.5, 20.0, 15.0, 2.0));
    }

    @Test
    void testGetInstanceInvalidAuthor() {
        assertThrows(BuildException.class, () -> Book.getInstance("BOOK001", "Book Name", "Description", 29.99, 10, true, "978-3-16-148410-0", "Book Title", "", "Publisher", 2020, 1.5, 20.0, 15.0, 2.0));
    }

    @Test
    void testGetInstanceInvalidPublisher() {
        assertThrows(BuildException.class, () -> Book.getInstance("BOOK001", "Book Name", "Description", 29.99, 10, true, "978-3-16-148410-0", "Book Title", "Author Name", "P", 2020, 1.5, 20.0, 15.0, 2.0));
    }

    @Test
    void testGetInstanceInvalidPublishYear() {
        assertThrows(BuildException.class, () -> Book.getInstance("BOOK001", "Book Name", "Description", 29.99, 10, true, "978-3-16-148410-0", "Book Title", "Author Name", "Publisher", -1, 1.5, 20.0, 15.0, 2.0));
    }

    @Test
    void testGetInstanceInvalidPhysicalData() {
        assertThrows(BuildException.class, () -> Book.getInstance("BOOK001", "Book Name", "Description", 29.99, 10, true, "978-3-16-148410-0", "Book Title", "Author Name", "Publisher", 2020, 0, 20.0, 15.0, 2.0));
    }

    @Test
    void testSetIsbnValid() throws BuildException {
        Book book = Book.getInstance("BOOK001", "Book Name", "Description", 29.99, 10, true, "978-3-16-148410-0", "Book Title", "Author Name", "Publisher", 2020, 1.5, 20.0, 15.0, 2.0);
        assertEquals(0, book.setIsbn("1234567890"));
        assertEquals("1234567890", book.getIsbn());
    }

    @Test
    void testSetIsbnInvalid() throws BuildException {
        Book book = Book.getInstance("BOOK001", "Book Name", "Description", 29.99, 10, true, "978-3-16-148410-0", "Book Title", "Author Name", "Publisher", 2020, 1.5, 20.0, 15.0, 2.0);
        assertEquals(-1, book.setIsbn(null));
    }

    @Test
    void testSetTitleValid() throws BuildException {
        Book book = Book.getInstance("BOOK001", "Book Name", "Description", 29.99, 10, true, "978-3-16-148410-0", "Book Title", "Author Name", "Publisher", 2020, 1.5, 20.0, 15.0, 2.0);
        assertEquals(0, book.setTitle("New Title"));
        assertEquals("New Title", book.getTitle());
    }

    @Test
    void testSetTitleInvalid() throws BuildException {
        Book book = Book.getInstance("BOOK001", "Book Name", "Description", 29.99, 10, true, "978-3-16-148410-0", "Book Title", "Author Name", "Publisher", 2020, 1.5, 20.0, 15.0, 2.0);
        assertEquals(-1, book.setTitle(""));
    }

    @Test
    void testSetAuthorValid() throws BuildException {
        Book book = Book.getInstance("BOOK001", "Book Name", "Description", 29.99, 10, true, "978-3-16-148410-0", "Book Title", "Author Name", "Publisher", 2020, 1.5, 20.0, 15.0, 2.0);
        assertEquals(0, book.setAuthor("New Author"));
        assertEquals("New Author", book.getAuthor());
    }

    @Test
    void testSetAuthorInvalid() throws BuildException {
        Book book = Book.getInstance("BOOK001", "Book Name", "Description", 29.99, 10, true, "978-3-16-148410-0", "Book Title", "Author Name", "Publisher", 2020, 1.5, 20.0, 15.0, 2.0);
        assertEquals(-1, book.setAuthor(""));
    }

    @Test
    void testSetPublisherValid() throws BuildException {
        Book book = Book.getInstance("BOOK001", "Book Name", "Description", 29.99, 10, true, "978-3-16-148410-0", "Book Title", "Author Name", "Publisher", 2020, 1.5, 20.0, 15.0, 2.0);
        assertEquals(0, book.setPublisher("New Publisher"));
        assertEquals("New Publisher", book.getPublisher());
    }

    @Test
    void testSetPublisherInvalid() throws BuildException {
        Book book = Book.getInstance("BOOK001", "Book Name", "Description", 29.99, 10, true, "978-3-16-148410-0", "Book Title", "Author Name", "Publisher", 2020, 1.5, 20.0, 15.0, 2.0);
        assertEquals(-1, book.setPublisher("P"));
    }

    @Test
    void testSetPublishYearValid() throws BuildException {
        Book book = Book.getInstance("BOOK001", "Book Name", "Description", 29.99, 10, true, "978-3-16-148410-0", "Book Title", "Author Name", "Publisher", 2020, 1.5, 20.0, 15.0, 2.0);
        assertEquals(0, book.setPublishYear(2021));
        assertEquals(2021, book.getPublishYear());
    }

    @Test
    void testSetPublishYearInvalid() throws BuildException {
        Book book = Book.getInstance("BOOK001", "Book Name", "Description", 29.99, 10, true, "978-3-16-148410-0", "Book Title", "Author Name", "Publisher", 2020, 1.5, 20.0, 15.0, 2.0);
        assertEquals(-1, book.setPublishYear(-1));
    }

    @Test
    void testIsAvailable() throws BuildException {
        Book book = Book.getInstance("BOOK001", "Book Name", "Description", 29.99, 10, true, "978-3-16-148410-0", "Book Title", "Author Name", "Publisher", 2020, 1.5, 20.0, 15.0, 2.0);
        assertEquals(true, book.isAvailable());
    }

    @Test
    void testGetVolume() throws BuildException {
        Book book = Book.getInstance("BOOK001", "Book Name", "Description", 29.99, 10, true, "978-3-16-148410-0", "Book Title", "Author Name", "Publisher", 2020, 1.5, 20.0, 15.0, 2.0);
        assertEquals(600.0, book.getVolume());
    }

    @Test
    void testGetArea() throws BuildException {
        Book book = Book.getInstance("BOOK001", "Book Name", "Description", 29.99, 10, true, "978-3-16-148410-0", "Book Title", "Author Name", "Publisher", 2020, 1.5, 20.0, 15.0, 2.0);
        assertEquals(300.0, book.getArea());
    }
}
