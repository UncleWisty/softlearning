package com.example.core.entities.client.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class ClientDTO {

    @Id
    @Column(name = "id_client")
    private int idClient;
    @Column(name = "id_person")
    private String idPerson;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "adress")
    private String adress;
    @Column(name = "name_person")
    private String namePerson;
    @Column(name = "registration_date")
    private String registrationDate;

    public ClientDTO() {
    }

    public ClientDTO(String idPerson, String email, String phone, String adress, String namePerson,
            String registrationDate, int idClient) {
        this.idPerson = idPerson;
        this.email = email;
        this.phone = phone;
        this.adress = adress;
        this.namePerson = namePerson;
        this.registrationDate = registrationDate;
        this.idClient = idClient;
    }

    public String getIdPerson() {
        return idPerson;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAdress() {
        return adress;
    }

    public String getNamePerson() {
        return namePerson;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setNamePerson(String namePerson) {
        this.namePerson = namePerson;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Override
    public String toString() {
        return "ClientDTO [idPerson=" + idPerson + ", email=" + email + ", phone=" + phone + ", adress=" + adress
                + ", namePerson=" + namePerson + ", registrationDate=" + registrationDate + ", idClient=" + idClient
                + "]";
    }

}
