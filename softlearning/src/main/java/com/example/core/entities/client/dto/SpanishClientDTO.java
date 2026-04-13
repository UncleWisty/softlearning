package com.example.core.entities.client.dto;

import com.fasterxml.jackson.annotation.JsonGetter;

public class SpanishClientDTO {

    private String idPerson, email, phone, adress, namePerson, registrationDate;
    private int idClient;

    public SpanishClientDTO() {
    }

    public SpanishClientDTO(String idPerson, String email, String phone, String adress, String namePerson,
            String registrationDate, int idClient) {
        this.idPerson = idPerson;
        this.email = email;
        this.phone = phone;
        this.adress = adress;
        this.namePerson = namePerson;
        this.registrationDate = registrationDate;
        this.idClient = idClient;
    }

    @JsonGetter("id_persona")
    public String getIdPerson() {
        return idPerson;
    }

    @JsonGetter("correo_electronico")
    public String getEmail() {
        return email;
    }

    @JsonGetter("telefono")
    public String getPhone() {
        return phone;
    }

    @JsonGetter("direccion")
    public String getAdress() {
        return adress;
    }

    @JsonGetter("nombre")
    public String getNamePerson() {
        return namePerson;
    }

    @JsonGetter("fecha_registro")
    public String getRegistrationDate() {
        return registrationDate;
    }

    @JsonGetter("codigo_cliente")
    public int getIdClient() {
        return idClient;
    }

    @JsonGetter("id_persona")
    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
    }

    @JsonGetter("correo_electronico")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonGetter("telefono")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonGetter("direccion")
    public void setAdress(String adress) {
        this.adress = adress;
    }

    @JsonGetter("nombre")
    public void setNamePerson(String namePerson) {
        this.namePerson = namePerson;
    }

    @JsonGetter("fecha_registro")
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    @JsonGetter("codigo_cliente")
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Override
    public String toString() {
        return "SpanishClientDTO [id_persona=" + idPerson + ", correo_electronico=" + email + ", telefono=" + phone + ", direccion=" + adress
                + ", nombre=" + namePerson + ", fecha_registro=" + registrationDate + ", id_cliente=" + idClient
                + "]";
    }

}
