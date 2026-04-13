package com.example.core.entities.client.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.example.core.entities.client.dto.ClientDTO;
import com.example.core.entities.client.model.Client;
import com.example.shared.exceptions.BuildException;

public class ClientMapperTest {

    @Test
    void testClientToDTO() throws BuildException {
        Client client = Client.getInstance("12345678", "test@example.com", "123456789", "Test Address 123", "Test Name", 1001, "01-01-2020, 12:00:00");
        ClientDTO dto = ClientMapper.ClientToDTO(client);
        assertEquals("12345678", dto.getIdPerson());
        assertEquals("test@example.com", dto.getEmail());
        assertEquals("123456789", dto.getPhone());
        assertEquals("Test Address 123", dto.getAdress());
        assertEquals("Test Name", dto.getNamePerson());
        assertEquals("01-01-2020, 12:00:00", dto.getRegistrationDate());
        assertEquals(1001, dto.getIdClient());
    }

    @Test
    void testDTOtoClientValid() throws BuildException {
        ClientDTO dto = new ClientDTO("12345678", "test@example.com", "123456789", "Test Address 123", "Test Name", "01-01-2020, 12:00:00", 1001);
        Client client = ClientMapper.DTOtoClient(dto);
        assertEquals("12345678", client.getIdPerson());
        assertEquals("test@example.com", client.getEmail());
        assertEquals("123456789", client.getPhone());
        assertEquals("Test Address 123", client.getAdress());
        assertEquals("Test Name", client.getNamePerson());
        assertEquals("01-01-2020, 12:00:00", client.getRegistrationDate());
        assertEquals(1001, client.getIdClient());
    }

    @Test
    void testDTOtoClientInvalid() {
        ClientDTO dto = new ClientDTO("123", "test", "123", "Test", "Te", "invalid", 999);
        assertThrows(BuildException.class, () -> ClientMapper.DTOtoClient(dto));
    }
}
