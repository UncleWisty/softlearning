package com.example.core.entities.course.persistence;

import java.util.List;
import java.util.Optional;

import com.example.core.entities.course.dto.CourseDTO;

public interface CourseRepository {

    public List<CourseDTO> findAll();

    public Optional<CourseDTO> findByIdProduct(String id);

    public List<CourseDTO> findByName(String name);

    public CourseDTO save(CourseDTO course);

    public void deleteByIdProduct(String id);

}

