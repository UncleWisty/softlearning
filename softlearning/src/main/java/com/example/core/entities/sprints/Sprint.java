package com.example.core.entities.sprints;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.example.core.entities.shared.validations.Check;
import com.example.shared.exceptions.BuildException;
import com.example.shared.exceptions.GeneralDateTimeException;

public class Sprint {

    protected int id;
    protected LocalDateTime startDate, finishDate;
    protected ArrayList<Task> tasks;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss");

    private Sprint(){

    }

    public static Sprint getInstance(int id, String startDate, String finishDate) throws BuildException{
        Sprint s = new Sprint();
        String errorMessage = "";

        try{
            s.checkSprintData(id, startDate, finishDate);
            s.tasks = new ArrayList<>();
        }catch(BuildException e){
            errorMessage+= e.getMessage();
        }

        if(errorMessage.length() > 0){
            throw new BuildException(errorMessage);
        }
        return s;
    }

    public void checkSprintData(int id, String startDate, String finishDate) throws BuildException{
        String errorMessage = "";
        try{
            if(this.setId(id) != 0){
                errorMessage += "Id invalido";
            }
            if(errorMessage.length() > 0){
            throw new BuildException("Error al validar datos: " + errorMessage);
            }
            this.setStartDate(startDate);
            this.setFinishDate(finishDate);

        }catch(GeneralDateTimeException e){
            throw new BuildException("Error al validar datos: " + e.getMessage());
        }
    }


    //task admin
    
    public int getNumTasks() {
        return this.tasks.size();
    }

    public void setTask(int id, String description, String responsible, int duration) throws BuildException{
        try{
            Task t = Task.getInstance(id, description, responsible, duration);
            this.tasks.add(t);
        }catch(BuildException e){
            throw new BuildException(e.getMessage() + ". ");
        }catch(Exception e){
            throw new BuildException(e.getMessage() + ". ");
        }
    }

    public String getTask(int pos) {
        return this.tasks.get(pos).getId()
                + "," + this.tasks.get(pos).getDescription()
                + "," + this.tasks.get(pos).getResponsible()
                + "," + this.tasks.get(pos).getDuration();
    }

    public void deleteTask(int pos) throws BuildException{
        try{
            this.tasks.remove(pos);
        }catch(Exception e){
            throw new BuildException("No se puede eliminar el task: " + e.getMessage());
        }
    }

    public int getTotalDuration(){
        int totalDuration = 0;
        for(Task task : this.tasks){
            totalDuration += task.getDuration();
        }
        return totalDuration;
    }

    public String getTasks() {
        String output = "";
        for (int pos = 0; pos < this.getNumTasks(); pos++) {
            output += this.getTask(pos) + ";";
        }
        return output;
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

    public String getStartDate(){
        try{
            String startdate =  Check.convertDateTimeToString(this.startDate, this.formatter);
            return startdate;
        }catch(GeneralDateTimeException e){
            return "";
        }
    }

    public void setStartDate(String startDate) throws GeneralDateTimeException{
        if(startDate != null){
            try{
                this.startDate = Check.convertStringToDateTime(startDate, this.formatter);

            }catch(GeneralDateTimeException e){
                throw new GeneralDateTimeException("Start date invalido. ");
            }
        }
    }

    public String getFinishDate() {
        try{
            String finishdate =  Check.convertDateTimeToString(this.finishDate, this.formatter);
            return finishdate;
        }catch(GeneralDateTimeException e){
            return "";
        }

    }

    public void setFinishDate(String finishDate) throws GeneralDateTimeException{
        if(finishDate != null){
            try{
                this.finishDate = Check.convertStringToDateTime(finishDate, this.formatter);

            }catch(GeneralDateTimeException e){
                throw new GeneralDateTimeException("Finish date invalido. ");
            }
        }
    }


    

}
