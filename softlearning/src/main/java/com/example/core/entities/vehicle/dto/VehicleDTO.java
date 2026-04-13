package com.example.core.entities.vehicle.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehicles")
public class VehicleDTO {

    @Id
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "marca")
    private String marca;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "caracteristicas")
    private String caracteristicas;
    @Column(name = "carga")
    private double carga;
    @Column(name = "capacidad")
    private double capacidad;
    @Column(name = "adquisicion")
    private String adquisicion;
    @Column(name = "revision")
    private String revision;


    public VehicleDTO() {
    }

    public VehicleDTO(String matricula, String marca, String modelo, String caracteristicas, double carga, double capacidad, String adquisicion, String revision) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.caracteristicas = caracteristicas;
        this.carga = carga;
        this.capacidad = capacidad;
        this.adquisicion = adquisicion;
        this.revision = revision;
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
        return this.adquisicion;
    }

    public String getRevision() {
        return this.revision;
    }

    public void setMatricula(String matricula){
        this.matricula = matricula;
    }

    public void setMarca(String marca){
        this.marca = marca;
    }

    public void setModelo(String modelo){
        this.modelo = modelo;
    }

    public void setCaracteristicas(String caracteristicas){
        this.caracteristicas = caracteristicas;
    }

    public void setCarga(double carga){
        this.carga = carga;
    }

    public void setCapacidad(double capacidad){
        this.capacidad = capacidad;
    }

    public void setAdquisicion(String adquisicion){
        this.adquisicion = adquisicion;
    }

    public void setRevision(String revision){
        this.revision = revision;
    }

    @Override
    public String toString() {
        return "VehicleDTO [getMatricula()=" + getMatricula() + ", getMarca()="
                + getMarca() + ", getModelo()=" + getModelo() + ", getCaracteristicas()=" + getCaracteristicas()
                + ", getCarga()=" + getCarga() + ", getCapacidad()=" + getCapacidad() + ", getAdquisicion()="
                + getAdquisicion() + ", getRevision()=" + getRevision() + "]";
    }

    
}
