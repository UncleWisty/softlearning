package com.example.core.entities.vehicle.mapper;

import com.example.core.entities.vehicle.dto.VehicleDTO;
import com.example.core.entities.vehicle.model.Vehicle;
import com.example.shared.exceptions.BuildException;

public class VehicleMapper {

    public static VehicleDTO VehicleToDTO(Vehicle v) {
        return new VehicleDTO(
                v.getMatricula(),
                v.getMarca(),
                v.getModelo(),
                v.getCaracteristicas(),
                v.getCarga(),
                v.getCapacidad(),
                v.getAdquisicion(),
                v.getRevision()
        );
    }

    public static Vehicle DTOtoVehicle(VehicleDTO dto) throws BuildException {
        return Vehicle.getInstance(
                dto.getMatricula(),
                dto.getMarca(),
                dto.getModelo(),
                dto.getCaracteristicas(),
                dto.getCarga(),
                dto.getCapacidad(),
                dto.getAdquisicion(),
                dto.getRevision()
        );
    }

}
