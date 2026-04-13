package com.example.infraestructure.persistence.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.core.entities.client.dto.ClientDTO;
import com.example.core.entities.client.persistence.ClientRepository;

@Repository
public interface JpaClientRepository extends JpaRepository<ClientDTO, Integer>, ClientRepository {

    @Override
    public Optional<ClientDTO> findByIdClient(int id);

    @Override
    public List<ClientDTO> findByNamePerson(String name);

    @Override
    @Query(value = "SELECT c FROM ClientDTO c WHERE c.namePerson LIKE %:name%")
    public List<ClientDTO> findByPartialName(String name);

    @Override
    @Query(value = "SELECT count(*) FROM ClientDTO c WHERE c.namePerson LIKE %:name%")
    public Integer countByPartialName(String name);

    @Override
    public ClientDTO save(ClientDTO client);

    @Override
    public void deleteByIdClient(int id);
}
