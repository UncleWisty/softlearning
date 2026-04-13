package com.example.core.entities.sprints;

import com.example.shared.exceptions.BuildException;

public class Task {

    protected int id, duration;
    protected String description, responsible;

    protected Task(){}

    //acabo de darme cuenta que estoy repitiendo cosas que tonteria pero no me da tiempo
    public static Task getInstance(int id, String description, String responsible, int duration) throws BuildException{
        Task t = new Task();
        String errorMessage = taskDataValidation(id, description, responsible, duration);
        if (!errorMessage.isEmpty()) {
            throw new BuildException(errorMessage);
        }

        t.setId(id);
        t.setDescription(description);
        t.setResponsible(responsible);
        t.setDuration(duration);
        return t;

    }



    private static String taskDataValidation(int id, String description, String responsible, int duration) {
        String errorMessage = "";

        if(id < 0){
            errorMessage += "Id invalido. ";
        }
        if(description.length() > 250){
            errorMessage += "Descripcion invalida, no supere los 250 caracteres. ";
        }
        if (responsible.length() < 0) {
            errorMessage += "Codigo de responsable invalido. ";
        }
        if (duration < 0) {
            errorMessage += "Duracion invalida. ";
        }
        return errorMessage;
    }
    
    
    
    //basic getters and setters
    public int getId() {
        return id;
    }

    public int setId(int id) {
        if(id > 0){
            this.id = id;
            return 0;
        }
        return -1;
    }

    public String getResponsible() {
        return responsible;
    }

    public int setResponsible(String responsible) {
        if(responsible.length() > 0){
            this.responsible = responsible;
            return 0;
        }
        return -1;
    }

    public String getDescription() {
        return description;
    }

    public int setDescription(String description) {
        if(description.length() > 250){
            this.description = description;
            return 0;
        }
        return -1;
    }

    public int getDuration() {
        return duration;
    }

    public int setDuration(int duration) {
        if(duration > 0){
            this.duration = duration;
            return 0;
        }
        return -1;
    }




    
}
