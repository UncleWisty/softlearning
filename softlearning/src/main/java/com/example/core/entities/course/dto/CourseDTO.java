package com.example.core.entities.course.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses")
public class CourseDTO {

    @Id
    @Column(name = "id_product")
    private String idProduct;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "stock")
    private int stock;
    @Column(name = "price")
    private double price;
    @Column(name = "id_course")
    private String idCourse;
    @Column(name = "duration")
    private double duration;
    @Column(name = "is_available")
    private boolean isAvailable;

    public CourseDTO() {
    }

    public CourseDTO(String idProduct, String name, String description, double price, int stock, boolean available, String idCourse, double duration) {
        this.idProduct = idProduct;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.idCourse = idCourse;
        this.duration = duration;
        this.isAvailable = available;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public String getIdCourse(){
        return idCourse;
    }

    public double getDuration(){
        return duration;
    }
    public boolean isAvailable(){
        return isAvailable;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setIdCourse(String idCourse){
        this.idCourse = idCourse;
    }

    public void setDuration(double duration){
        this.duration = duration;
    }

    public void setAvailable(boolean available){
        this.isAvailable = available;
    }

}
