package com.example.core.entities.vehicle.appservices;

import org.springframework.stereotype.Service;

import com.example.shared.exceptions.ServiceException;

@Service
public interface VehicleServices {

    public String getAllVehiclesToJson() throws ServiceException;

    public String getByMatriculaToJson(String matricula) throws ServiceException;

    public String addFromJson(String vehicle) throws ServiceException;

    public String updateOneFromJson(String vehicle) throws ServiceException;

    public void deleteByMatricula(String matricula) throws ServiceException;
}
