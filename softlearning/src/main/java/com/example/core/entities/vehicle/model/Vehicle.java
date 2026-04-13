package com.example.core.entities.vehicle.model;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.shared.exceptions.BuildException;

public class Vehicle{

    protected String matricula, marca, modelo, caracteristicas;
    protected double carga, capacidad;
    protected LocalDateTime adquisicion, revision;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm:ss");

    public Vehicle() {
    }

    public static Vehicle getInstance(String matricula, String marca, String modelo, String caracteristicas, double carga, double capacidad, String adquisicion, String revision) throws BuildException {
        Vehicle v = new Vehicle();
        String message = v.vehicleDataValidation(matricula, marca, modelo, caracteristicas, carga, capacidad, adquisicion, revision);
        if (!message.isEmpty()) {
            throw new BuildException(message);
        }
        return v;
    }

    private String vehicleDataValidation(String matricula, String marca, String modelo, String caracteristicas, double carga, double capacidad, String adquisicion, String revision) throws BuildException {
        String errorMessage = "";

        if (setMatricula(matricula) != 0) {
            errorMessage += "matricula incorrecta. ";
        }
        if (setMarca(marca) != 0) {
            errorMessage += "marca incorrecto. ";
        }
        if (setModelo(modelo) != 0) {
            errorMessage += "modelo incorrecto. ";
        }
        if (setCaracteristicas(caracteristicas) != 0) {
            errorMessage += "caracteristicas incorrectas. ";
        }
        if (setCarga(carga) != 0) {
            errorMessage += "carga incorrecta. ";
        }
        if (setCapacidad(capacidad) != 0) {
            errorMessage += "capacidad incorrecta. ";
        }

        try {
            setAdquisicion(adquisicion);
        } catch (DateTimeException e) {
            errorMessage += "fecha de adquisicion incorrecta: " + e.getMessage();
        }

        try {
            setRevision(revision);
        } catch (DateTimeException e) {
            errorMessage += "fecha de adquisicion incorrecta: " + e.getMessage();
        }
        return errorMessage;
    }


    //setters
    public int setMatricula(String matricula){
        if(matricula.length() == 7){
            //length 7 porque estamos en españa jose!
            this.matricula = matricula;
            return 0;
        }
        return -1;
    }

    public int setMarca (String marca){
        if(marca != null){
            this.marca = marca;
            return 0;
        }
        return -1;
    }

    public int setModelo (String modelo){
        if(modelo != null){
            this.modelo = modelo;
            return 0;
        }
        return -1;
    }

    public int setCaracteristicas (String carac){
        if(carac.length() < 500){
            this.caracteristicas = carac;
            return 0;
        }
        return -1;
    }

    public int setCarga(double carga){
        if(carga > 0){
            this.carga = carga;
            return 0;
        }
        return -1;
    }

    public int setCapacidad(double capacidad){
        if(capacidad > 0){
            this.capacidad = capacidad;
            return 0;
        }
        return -1;
    }

    public int setAdquisicion (String adquisicion)throws DateTimeException{
        try {
            this.adquisicion = LocalDateTime.parse(adquisicion, formatter);
            return 0;
        } catch (DateTimeException e) {
            throw e;
        }
    }

    public int setRevision (String revision)throws DateTimeException{
        try {
            this.revision = LocalDateTime.parse(revision, formatter);
            return 0;
        } catch (DateTimeException e) {
            throw e;
        }
    }

    public String getMatricula(){
        return this.matricula;
    }

    public String getMarca(){
        return this.marca;
    }

    public String getModelo(){
        return this.modelo;
    }

    public String getCaracteristicas(){
        return this.caracteristicas;
    }

    public double getCarga(){
        return this.carga;
    }

    public double getCapacidad(){
        return this.capacidad;
    }

    public String getAdquisicion() {
        return this.adquisicion.format(formatter);
    }

    public String getRevision() {
        return this.revision.format(formatter);
    }



}
