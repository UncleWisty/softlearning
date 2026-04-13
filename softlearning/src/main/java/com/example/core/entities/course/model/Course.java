package com.example.core.entities.course.model;

import com.example.core.entities.shared.products.Product;
import com.example.core.entities.shared.validations.Check;
import com.example.shared.exceptions.BuildException;

public class Course extends Product {

    private String idCourse;
    private double duration;

    protected Course() {
    }

    public static Course getInstance(String idProduct, String name, String description, double price, int stock, boolean isAvailable, String idCourse, double duration) throws BuildException {
        Course c = new Course();
        String message = c.courseDataValidation(idProduct, name, description, price, stock, idCourse, duration);
        if (!message.isEmpty()) {
            throw new BuildException(message);
        }
        return c;
    }

    private String courseDataValidation(String idProduct, String name, String description, double price, int stock, String idCourse, double duration) {
        String errorMessage = "";
        //validacion de los campos del producto
        errorMessage += productDataValidation(idProduct, name, description, price, stock);
        //validacion de los campos del curso
        if (setIdCourse(idCourse) != 0) {
            errorMessage += "ID del curso incorrecto. ";
        }
        if (setDuration(duration) != 0) {
            errorMessage += "Duracion del curso incorrecta. ";
        }

        return errorMessage;

    }

    public String getIdCourse() {
        return idCourse;
    }

    public int setIdCourse(String idCourse) {
        if (Check.minStringChars(idCourse, 4)) {
            this.idCourse = idCourse;
            return 0;
        }
        return -1;
    }

    public double getDuration() {
        return duration;
    }

    public int setDuration(double duration) {
        if (Check.isValidNumber(duration, 0)) {
            this.duration = duration;
            return 0;
        }
        return -1;
    }

}
