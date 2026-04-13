package com.example.core.entities.client.model;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.core.entities.shared.stakeholders.Person;
import com.example.core.entities.shared.stakeholders.Stakeholder;
import com.example.core.entities.shared.validations.Check;
import com.example.shared.exceptions.BuildException;

public class Client extends Person implements Stakeholder {

    protected int idClient;
    protected LocalDateTime registrationDate;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm:ss");

    protected Client() {
    }

    public static Client getInstance(String idPerson, String email, String phone, String adress, String namePerson, int idClient, String registrationDate) throws BuildException {
        Client c = new Client();
        String message = c.PersonDataValidation(idPerson, email, phone, adress, namePerson);

        if (c.setIdClient(idClient) != true) {
            message += "ID Cliente incorrecto. ";
        }

        try {
            c.setRegistrationDate(registrationDate);
        } catch (DateTimeException e) {
            message += "Fecha de registro incorrecta: " + e.getMessage();
        }

        if (!message.isEmpty()) {
            throw new BuildException(message);
        }
        return c;
    }

    public int getIdClient() {
        return idClient;
    }

    public boolean setIdClient(int idClient) {
        if (Check.isValidNumber(idClient, 1000)) {
            this.idClient = idClient;
            return true;
        }
        return false;
    }

    public String getRegistrationDate() {
        return this.registrationDate.format(formatter);
    }

    public void setRegistrationDate(String registrationDate) throws DateTimeException {
        try {
            this.registrationDate = LocalDateTime.parse(registrationDate, formatter);
        } catch (DateTimeException e) {
            throw e;
        }
    }

    public int yearsOld() {
        return (LocalDateTime.now().getYear() - this.registrationDate.getYear());
    }

    @Override
    public String toString() {
        return "Client [getIdClient()=" + getIdClient() + ", getIdPerson()=" + getIdPerson()
                + ", getRegistrationDate()=" + getRegistrationDate() + ", getEmail()=" + getEmail() + ", getPhone()="
                + getPhone() + ", getAdress()=" + getAdress() + ", getNamePerson()=" + getNamePerson() + "]";
    }

    //sobreescritura de metodos
    @Override
    public String getContactData() {
        return super.getContactData() + "|" + this.getIdClient();
    }

    @Override
    public String ident() {
        return String.valueOf(getIdClient());
    }

    @Override
    public String name() {
        return this.getNamePerson();
    }

    @Override
    public String contactData() {
        return this.getContactData();
    }

}
