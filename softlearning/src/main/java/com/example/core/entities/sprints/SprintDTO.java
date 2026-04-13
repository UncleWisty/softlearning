package com.example.core.entities.sprints;


public class SprintDTO {

    private final int id;
    private final String startDate, finishDate, tasks;
    // private final List<TaskDTO> tasks;


    // public SprintDTO(int id, String startDate, String finishDate, List<TaskDTO> tasks){
    //     this.id = id;
    //     this.startDate = startDate;
    //     this.finishDate = finishDate;
    //     this.tasks = tasks;
    // }

    public SprintDTO(int id, String startDate, String finishDate, String tasks){
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.tasks = tasks;
    }


    public int getId() {
        return id;
    }


    public String getStartDate() {
        return startDate;
    }


    public String getFinishDate() {
        return finishDate;
    }


    public String getTasks() {
        return tasks;
    }


    // public List<TaskDTO> getTasks() {
    //     return tasks;
    // }

    

    

}
