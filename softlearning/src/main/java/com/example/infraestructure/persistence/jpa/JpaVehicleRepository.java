package com.example.infraestructure.persistence.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.core.entities.vehicle.dto.VehicleDTO;
import com.example.core.entities.vehicle.persistence.VehicleRepository;

@Repository
public interface JpaVehicleRepository extends JpaRepository<VehicleDTO, String>, VehicleRepository {

    @Override
    public Optional<VehicleDTO> findByMatricula(String matricula);

    @Override
    public VehicleDTO save(VehicleDTO vehicle);

    @Override
    public void deleteByMatricula(String vehicle);
}
