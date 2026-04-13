package com.example.core.entities.vehicle.persistence;

import java.util.List;
import java.util.Optional;

import com.example.core.entities.vehicle.dto.VehicleDTO;

public interface VehicleRepository {

    public List<VehicleDTO> findAll();

    public Optional<VehicleDTO> findByMatricula(String matricula);

    public VehicleDTO save(VehicleDTO book);

    public void deleteByMatricula(String id);

}
