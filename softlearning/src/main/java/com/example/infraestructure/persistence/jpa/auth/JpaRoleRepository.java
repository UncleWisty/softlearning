package com.example.infraestructure.persistence.jpa.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.core.entities.auth.dto.RoleEntity;
import com.example.core.entities.auth.dto.RoleEnum;

@Repository
public interface JpaRoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRoleEnum(RoleEnum roleEnum);
}
