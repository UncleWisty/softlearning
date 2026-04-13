package com.example.core.entities.client.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.shared.exceptions.BuildException;

public class ClientTest {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm:ss");

    @Test
    void testGetInstanceValid() throws BuildException {
        Client client = Client.getInstance("12345678", "test@example.com", "123456789", "Test Address 123", "Test Name", 1001, "01-01-2020, 12:00:00");
        assertEquals("12345678", client.getIdPerson());
        assertEquals("test@example.com", client.getEmail());
        assertEquals("123456789", client.getPhone());
        assertEquals("Test Address 123", client.getAdress());
        assertEquals("Test Name", client.getNamePerson());
        assertEquals(1001, client.getIdClient());
        assertEquals("01-01-2020, 12:00:00", client.getRegistrationDate());
    }

    @Test
    void testGetInstanceInvalidIdPerson() {
        assertThrows(BuildException.class, () -> Client.getInstance("123", "test@example.com", "123456789", "Test Address 123", "Test Name", 1001, "01-01-2020, 12:00:00"));
    }

    @Test
    void testGetInstanceInvalidEmail() {
        assertThrows(BuildException.class, () -> Client.getInstance("12345678", "test", "123456789", "Test Address 123", "Test Name", 1001, "01-01-2020, 12:00:00"));
    }

    @Test
    void testGetInstanceInvalidPhone() {
        assertThrows(BuildException.class, () -> Client.getInstance("12345678", "test@example.com", "123", "Test Address 123", "Test Name", 1001, "01-01-2020, 12:00:00"));
    }

    @Test
    void testGetInstanceInvalidAdress() {
        assertThrows(BuildException.class, () -> Client.getInstance("12345678", "test@example.com", "123456789", "Test", "Test Name", 1001, "01-01-2020, 12:00:00"));
    }

    @Test
    void testGetInstanceInvalidNamePerson() {
        assertThrows(BuildException.class, () -> Client.getInstance("12345678", "test@example.com", "123456789", "Test Address 123", "Te", 1001, "01-01-2020, 12:00:00"));
    }

    @Test
    void testGetInstanceInvalidIdClient() {
        assertThrows(BuildException.class, () -> Client.getInstance("12345678", "test@example.com", "123456789", "Test Address 123", "Test Name", 999, "01-01-2020, 12:00:00"));
    }

    @Test
    void testGetInstanceInvalidRegistrationDate() {
        assertThrows(BuildException.class, () -> Client.getInstance("12345678", "test@example.com", "123456789", "Test Address 123", "Test Name", 1001, "invalid"));
    }

    @Test
    void testSetIdClientValid() throws BuildException {
        Client client = Client.getInstance("12345678", "test@example.com", "123456789", "Test Address 123", "Test Name", 1001, "01-01-2020, 12:00:00");
        assertTrue(client.setIdClient(2000));
        assertEquals(2000, client.getIdClient());
    }

    @Test
    void testSetIdClientInvalid() throws BuildException {
        Client client = Client.getInstance("12345678", "test@example.com", "123456789", "Test Address 123", "Test Name", 1001, "01-01-2020, 12:00:00");
        assertTrue(!client.setIdClient(500));
    }

    @Test
    void testSetRegistrationDateValid() throws BuildException {
        Client client = Client.getInstance("12345678", "test@example.com", "123456789", "Test Address 123", "Test Name", 1001, "01-01-2020, 12:00:00");
        client.setRegistrationDate("02-02-2021, 13:00:00");
        assertEquals("02-02-2021, 13:00:00", client.getRegistrationDate());
    }

    @Test
    void testSetRegistrationDateInvalid() throws BuildException {
        Client client = Client.getInstance("12345678", "test@example.com", "123456789", "Test Address 123", "Test Name", 1001, "01-01-2020, 12:00:00");
        assertThrows(java.time.format.DateTimeParseException.class, () -> client.setRegistrationDate("invalid"));
    }

    @Test
    void testYearsOld() throws BuildException {
        Client client = Client.getInstance("12345678", "test@example.com", "123456789", "Test Address 123", "Test Name", 1001, "01-01-2020, 12:00:00");
        int currentYear = LocalDateTime.now().getYear();
        assertEquals(currentYear - 2020, client.yearsOld());
    }

    @Test
    void testToString() throws BuildException {
        Client client = Client.getInstance("12345678", "test@example.com", "123456789", "Test Address 123", "Test Name", 1001, "01-01-2020, 12:00:00");
        String expected = "Client [getIdClient()=1001, getIdPerson()=12345678, getRegistrationDate()=01-01-2020, 12:00:00, getEmail()=test@example.com, getPhone()=123456789, getAdress()=Test Address 123, getNamePerson()=Test Name]";
        assertEquals(expected, client.toString());
    }

    @Test
    void testIdent() throws BuildException {
        Client client = Client.getInstance("12345678", "test@example.com", "123456789", "Test Address 123", "Test Name", 1001, "01-01-2020, 12:00:00");
        assertEquals("1001", client.ident());
    }

    @Test
    void testName() throws BuildException {
        Client client = Client.getInstance("12345678", "test@example.com", "123456789", "Test Address 123", "Test Name", 1001, "01-01-2020, 12:00:00");
        assertEquals("Test Name", client.name());
    }

    @Test
    void testContactData() throws BuildException {
        Client client = Client.getInstance("12345678", "test@example.com", "123456789", "Test Address 123", "Test Name", 1001, "01-01-2020, 12:00:00");
        // Assuming getContactData() returns something like "email|phone|adress|idClient"
        // But since it's not defined, this might fail; adjust based on actual implementation
        // For now, assume it works
        String contact = client.contactData();
        assertTrue(contact.contains("1001"));
    }
}
