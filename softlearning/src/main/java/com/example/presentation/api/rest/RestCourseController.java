package com.example.presentation.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.core.entities.course.appservices.CourseServices;
import com.example.shared.exceptions.ServiceException;


/*
COURSE (JSON)
Headers: Content-Type: application/json
Body (raw JSON):
{
  "idProduct": "P-123",
  "name": "Curso de Arquitectura de Software",
  "description": "Aprende patrones y buenas prácticas",
  "stock": 50,
  "price": 199.99,
  "idCourse": "C-789",
  "duration": 120.5,
  "available": true
}
 */
@RestController
@RequestMapping("/softlearning/courses")
public class RestCourseController {

    @Autowired
    CourseServices courseServices;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllBooksJson() {
        try {
            return ResponseEntity.ok(courseServices.getAllCoursesToJson());
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getJsonCourseByIdProduct(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok(courseServices.getByIdProductToJson(id));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> newCourseFromJson(@RequestBody String coursedata) {
        try {
            return ResponseEntity.ok(courseServices.addFromJson(coursedata));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateCourseFromJson(@PathVariable(value = "id") String id,
            @RequestBody String coursedata) {
        try {
            return ResponseEntity.ok(courseServices.updateOneFromJson(coursedata));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByID(@PathVariable(value = "id") String id) {
        try {
            courseServices.deleteByIdProduct(id);
            return ResponseEntity.ok().build();
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
