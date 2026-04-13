package com.example.core.entities.client.mapper;

import com.example.core.entities.client.dto.ClientDTO;
import com.example.core.entities.client.model.Client;
import com.example.shared.exceptions.BuildException;

public class ClientMapper {

    public static ClientDTO ClientToDTO(Client c) {
        return new ClientDTO(
                c.getIdPerson(),
                c.getEmail(),
                c.getPhone(),
                c.getAdress(),
                c.name(),
                c.getRegistrationDate(),
                c.getIdClient()
        );
    }

    public static Client DTOtoClient(ClientDTO dto) throws BuildException {
        return Client.getInstance(
                dto.getIdPerson(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getAdress(),
                dto.getNamePerson(),
                dto.getIdClient(),
                dto.getRegistrationDate());
    }
}
