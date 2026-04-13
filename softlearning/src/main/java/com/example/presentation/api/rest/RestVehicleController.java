package com.example.presentation.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.core.entities.vehicle.appservices.VehicleServices;
import com.example.shared.exceptions.ServiceException;


/*
VEHICLE (JSON)
{
  "matricula": "4413FXG",
  "marca": "Opel",
  "modelo": "Corsa",
  "caracteristicas": "Gran maletero, vintage, ergonomico, familiar",
  "carga": 205.6,
  "capacidad": 3000.2,
  "adquisicion": "02-01-2026, 10:35:00",
  "revision": "14-12-2027, 19:30:00"
}
 */
@RestController
@RequestMapping("/softlearning/vehicles")
public class RestVehicleController {

    @Autowired
    VehicleServices vehicleServices;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllBooksJson() {
        try {
            return ResponseEntity.ok(vehicleServices.getAllVehiclesToJson());
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/{matricula}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getJsonVehicleByMatricula(@PathVariable(value = "matricula") String matricula) {
        try {
            return ResponseEntity.ok(vehicleServices.getByMatriculaToJson(matricula));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> newVehicleFromJson(@RequestBody String vehicledata) {
        try {
            return ResponseEntity.ok(vehicleServices.addFromJson(vehicledata));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


    @PutMapping(value = "/{matricula}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateVehicleFromJson(@PathVariable(value = "matricula") String matricula,
            @RequestBody String coursedata) {
        try {
            return ResponseEntity.ok(vehicleServices.updateOneFromJson(coursedata));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


    @DeleteMapping("/{matricula}")
    public ResponseEntity<String> deleteByMatricula(@PathVariable(value = "matricula") String matricula) {
        try {
            vehicleServices.deleteByMatricula(matricula);
            return ResponseEntity.ok().build();
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
