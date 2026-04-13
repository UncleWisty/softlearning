package com.example.core.entities.book.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class BookDTO {

    @Id
    @Column(name = "id_product")
    private String idProduct;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "publish_year")
    private int publishYear;
    @Column(name = "stock")
    private int stock;
    @Column(name = "price")
    private double price;
    @Column(name = "weight")
    private double weight;
    @Column(name = "height")
    private double height;
    @Column(name = "width")
    private double width;
    @Column(name = "depth")
    private double depth;
    @Column(name = "is_available")
    private boolean isAvailable;

    public BookDTO() {
    }

    public BookDTO(String idProduct, String name, String description, double price, int stock, boolean available,
            String isbn, String title, String author, String publisher, int publishYear, double weight, double height, double width, double depth) {
        this.idProduct = idProduct;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.isAvailable = available;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.depth = depth;
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

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getDepth() {
        return depth;
    }

    public boolean isAvailable() {
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

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    @Override
    public String toString() {
        return "BookDTO [idProduct=" + idProduct + ", name=" + name + ", description=" + description + ", price="
                + price + ", stock=" + stock + ", isAvailable=" + isAvailable + ", isbn=" + isbn + ", title=" + title
                + ", author=" + author + ", publisher=" + publisher + ", publishYear=" + publishYear + ", weight="
                + weight + ", height=" + height + ", width=" + width + ", depth=" + depth + "]";
    }
}
