package com.example.core.entities.course.mapper;


import com.example.core.entities.course.dto.CourseDTO;
import com.example.core.entities.course.model.Course;
import com.example.shared.exceptions.BuildException;

public class CourseMapper {

    public static CourseDTO CourseToDTO(Course c) {
        return new CourseDTO(
                c.getIdProduct(),
                c.getName(),
                c.getDescription(),
                c.getPrice(),
                c.getStock(),
                c.isAvailable(),
                c.getIdCourse(),
                c.getDuration()
        );
    }

    public static Course DTOtoCourse(CourseDTO dto) throws BuildException {
        return Course.getInstance(
                dto.getIdProduct(),
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getStock(),
                dto.isAvailable(),
                dto.getIdCourse(),
                dto.getDuration()
        );
    }

}
