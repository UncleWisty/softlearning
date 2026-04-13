package com.example.core.entities.sprints;

import com.example.shared.exceptions.BuildException;

public class SprintMapper {

    public static SprintDTO SprintDTO(Sprint sprint) throws BuildException{
        if(sprint == null){
            throw new BuildException("Error al crear DTO");
        }

        return new SprintDTO(
            sprint.getId(),
            sprint.getStartDate(), 
            sprint.getFinishDate(), 
            sprint.getTasks());

    }

    //no voy a hacer el taskDTO pq no lo he usado al final
}
