package com.example.infraestructure.persistence.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.core.entities.course.dto.CourseDTO;
import com.example.core.entities.course.persistence.CourseRepository;

@Repository
public interface JpaCourseRepository extends JpaRepository<CourseDTO, String>, CourseRepository {

    @Override
    public Optional<CourseDTO> findByIdProduct(String id);

    @Override
    public List<CourseDTO> findByName(String name);


    @Override
    public CourseDTO save(CourseDTO course);

    @Override
    public void deleteByIdProduct(String id);
}
