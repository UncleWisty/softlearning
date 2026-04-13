package com.example.core.entities.client.persistence;

import java.util.List;
import java.util.Optional;

import com.example.core.entities.client.dto.ClientDTO;

public interface ClientRepository {

    public List<ClientDTO> findAll();

    public Optional<ClientDTO> findByIdClient(int id);

    public List<ClientDTO> findByNamePerson(String name);

    public List<ClientDTO> findByPartialName(String name);

    public Integer countByPartialName(String name);

    public ClientDTO save(ClientDTO client);

    public void deleteByIdClient(int id);

}
