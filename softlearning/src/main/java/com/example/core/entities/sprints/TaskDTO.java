package com.example.core.entities.sprints;

public class TaskDTO {

    private final int id, duration;
    private final String description, responsible;

    public TaskDTO(int id, String description, String responsible, int duration){
        this.id = id;
        this.description = description;
        this.responsible = responsible;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public int getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }

    public String getResponsible() {
        return responsible;
    }


    

}
