package com.example.core.entities.book.dto;

import com.fasterxml.jackson.annotation.JsonGetter;

public class SpanishBookDTO {

    private String idProduct, name, description, isbn, title, author, publisher;
    private int publishYear, stock;
    private double price, weight, height, width, depth;
    private boolean isAvailable;

    public SpanishBookDTO() {
    }

    public SpanishBookDTO(String idProduct, String name, String description, double price, int stock, boolean available,
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

    @JsonGetter("id_producto")
    public String getIdProduct() {
        return idProduct;
    }

    @JsonGetter("nombre")
    public String getName() {
        return name;
    }

    @JsonGetter("descripcion")
    public String getDescription() {
        return description;
    }

    @JsonGetter("isbn")
    public String getIsbn() {
        return isbn;
    }

    @JsonGetter("titulo")
    public String getTitle() {
        return title;
    }

    @JsonGetter("autor")
    public String getAuthor() {
        return author;
    }

    @JsonGetter("editorial")
    public String getPublisher() {
        return publisher;
    }

    @JsonGetter("ano_publicacion")
    public int getPublishYear() {
        return publishYear;
    }

    @JsonGetter("almacen")
    public int getStock() {
        return stock;
    }

    @JsonGetter("precio")
    public double getPrice() {
        return price;
    }

    @JsonGetter("peso")
    public double getWeight() {
        return weight;
    }

    @JsonGetter("altura")
    public double getHeight() {
        return height;
    }

    @JsonGetter("ancho")
    public double getWidth() {
        return width;
    }

    @JsonGetter("profundidad")
    public double getDepth() {
        return depth;
    }

    @JsonGetter("disponibilidad")
    public boolean isAvailable() {
        return isAvailable;
    }

    @JsonGetter("id_producto")
    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    @JsonGetter("nombre")
    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter("descripcion")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonGetter("isbn")
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @JsonGetter("titulo")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonGetter("autor")
    public void setAuthor(String author) {
        this.author = author;
    }

    @JsonGetter("editorial")
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @JsonGetter("ano_publicacion")
    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    @JsonGetter("almacen")
    public void setStock(int stock) {
        this.stock = stock;
    }

    @JsonGetter("precio")
    public void setPrice(double price) {
        this.price = price;
    }

    @JsonGetter("peso")
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @JsonGetter("altura")
    public void setHeight(double height) {
        this.height = height;
    }

    @JsonGetter("ancho")
    public void setWidth(double width) {
        this.width = width;
    }

    @JsonGetter("profundidad")
    public void setDepth(double depth) {
        this.depth = depth;
    }

    @JsonGetter("disponibilidad")
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    @Override
    public String toString() {
        return "BookDTO [id_producto=" + idProduct + ", nombre=" + name + ", descripcion=" + description + ", precio="
                + price + ", almacen=" + stock + ", disponibilidad=" + isAvailable + ", isbn=" + isbn + ", titulo=" + title
                + ", autor=" + author + ", editorial=" + publisher + ", ano_publicacion=" + publishYear + ", peso="
                + weight + ", altura=" + height + ", ancho=" + width + ", profundidad=" + depth + "]";
    }
}
