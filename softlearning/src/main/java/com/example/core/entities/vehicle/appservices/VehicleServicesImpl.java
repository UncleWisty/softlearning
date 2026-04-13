package com.example.core.entities.vehicle.appservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.example.core.entities.vehicle.dto.VehicleDTO;
import com.example.core.entities.vehicle.mapper.VehicleMapper;
import com.example.core.entities.vehicle.persistence.VehicleRepository;
import com.example.services.serializers.Serializer;
import com.example.services.serializers.Serializers;
import com.example.services.serializers.SerializersCatalog;
import com.example.shared.exceptions.BuildException;
import com.example.shared.exceptions.ServiceException;

@Controller
public class VehicleServicesImpl implements VehicleServices {

    @Autowired
    private VehicleRepository vehicleRepository;
    private Serializer<VehicleDTO> serializer;

    // ****** Implementing the business logic methods and common featues (clean code design) ******
    protected VehicleDTO getDTO(String id) {
        return vehicleRepository.findByMatricula(id).orElse(null);
    }

    protected VehicleDTO getByMatricula(String matricula) throws ServiceException {
        VehicleDTO vdto = this.getDTO(matricula);

        if (vdto == null) {
            throw new ServiceException("vehicle " + matricula + " not found");
        }
        return vdto;
    }

    protected VehicleDTO checkInputData(String vehicle) throws ServiceException {
        try {
            VehicleDTO vdto = (VehicleDTO) this.serializer.deserialize(vehicle, VehicleDTO.class);
            VehicleMapper.DTOtoVehicle(vdto);
            return vdto;
        } catch (BuildException e) {
            throw new ServiceException("error in the input vehicle data: " + e.getMessage());
        }
    }

    @Override
    public String getAllVehiclesToJson() throws ServiceException {
        Serializer<List<VehicleDTO>> listSerializer = (Serializer<List<VehicleDTO>>) SerializersCatalog.getInstance(Serializers.JSON_VEHICLE);
        return listSerializer.serialize(vehicleRepository.findAll());
    }

    protected VehicleDTO newVehicle(String vehicle) throws ServiceException {
        VehicleDTO vdto = this.checkInputData(vehicle);

        if (this.getDTO(vdto.getMatricula()) == null) {
            return vehicleRepository.save(vdto);
        }
        throw new ServiceException("vehicle " + vdto.getMatricula() + " already exists");
    }

    protected VehicleDTO updateVehicle(String vehicle) throws ServiceException {
        VehicleDTO vdto = this.checkInputData(vehicle);
        this.getByMatricula(vdto.getMatricula());
        return vehicleRepository.save(vdto);
    }

    // ****** Implementing the interface methods ******
    @Override
    public String getByMatriculaToJson(String matricula) throws ServiceException {
        this.serializer = (Serializer<VehicleDTO>) SerializersCatalog.getInstance(Serializers.JSON_VEHICLE);
        return serializer.serialize(this.getByMatricula(matricula));
    }

    @Override
    public String addFromJson(String vehicle) throws ServiceException {
        this.serializer = (Serializer<VehicleDTO>) SerializersCatalog.getInstance(Serializers.JSON_VEHICLE);
        return serializer.serialize(this.newVehicle(vehicle));
    }

    @Override
    public String updateOneFromJson(String vehicle) throws ServiceException {
        this.serializer = (Serializer<VehicleDTO>) SerializersCatalog.getInstance(Serializers.JSON_VEHICLE);
        return serializer.serialize(this.updateVehicle(vehicle));
    }

    @Override
    @Transactional
    public void deleteByMatricula(String matricula) throws ServiceException {
        this.getByMatricula(matricula);
        vehicleRepository.deleteByMatricula(matricula);
    }
}
