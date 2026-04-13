package com.example.core.entities.sprints.examTests;

import com.example.core.entities.sprints.Sprint;
import com.example.core.entities.sprints.SprintMapper;
import com.example.shared.exceptions.BuildException;

public class SprintFunctionalTest {

    public static void main(String[] args){

        System.out.println("=================================================");
        System.out.println("         TEST FUNCIONAL DE SPRINT Y TASKS        ");
        System.out.println("=================================================");

        try{
            Sprint s = Sprint.getInstance(1001, "30/11/2025-12:00:00", "15/12/2025-12:00:00");
            System.out.println("ID: " + s.getId());
            System.out.println("START DATE: " + s.getStartDate());
            System.out.println("FINISH DATE: " + s.getFinishDate());

            s.setTask(1,"Check Jose's exam grades", "Lorien Biela", 1);
            s.setTask(2,"Make a DB backup", "Juan Pelos", 3);
            s.setTask(3,"Make a stock market increase plan", "Adriana Ruiz", 5);
            System.out.println("Cantidad de tasks añadidas: " + s.getNumTasks());
            System.out.println("Duracion total: " + s.getTotalDuration() + "h");
            s.deleteTask(2);
            System.out.println("Eliminado el tercer task (2a posicion)");
            System.out.println("Cantidad de tasks restantes: " + s.getNumTasks());
            System.out.println("Duracion total: " + s.getTotalDuration() + "h");

            System.out.println("Task en la posicion 0:\n " + s.getTask(0));

            System.out.println("Devolvemos todos los tasks en CSV:\n " + s.getTasks());

            SprintMapper.SprintDTO(s);
            System.out.println("DTO creado correctamente");
            System.out.println("\n=======================================\n");

        }catch(BuildException e){
            System.out.println("TEST ERROR: " + e.getMessage());
        }catch(Exception e){
            System.out.println("ERROR GENERAL: " + e.getMessage());
        }

        //pruebas de fallos
        try{
            Sprint.getInstance(1002, "30/11/2025", "15/12/2025-12:00:00");
        }catch(BuildException e){
            System.out.println("CAPTURADO: " + e.getMessage());
        }

        try{
            Sprint s3 = Sprint.getInstance(1003, "30/11/2025-12:00:00", "15/12/2025-12:00:00");
            s3.setTask(-1,"Check Jose's exam grades", "Lorien Biela", 2);
            
        }catch(BuildException e){
            System.out.println("CAPTURADO: " + e.getMessage());
        }










    }
}
