package com.example.core.entities.book.model;

import com.example.core.entities.shared.physicals.PhysicalData;
import com.example.core.entities.shared.products.Marketable;
import com.example.core.entities.shared.products.Product;
import com.example.core.entities.shared.products.Storable;
import com.example.core.entities.shared.validations.Check;
import com.example.shared.exceptions.BuildException;

public class Book extends Product implements Storable, Marketable {

    protected String isbn, title, author, publisher;
    protected int publishYear;
    protected PhysicalData physicalData;

    public Book() {
    }

    public static Book getInstance(String idProduct, String name, String description, double price, int stock, boolean isAvailable, String isbn, String title,
            String author, String publisher, int publishYear, double weight, double height, double width,
            double depth) throws BuildException {
        Book b = new Book();
        String message = b.bookDataValidation(idProduct, name, description, price, stock, isbn, title, author, publisher, publishYear, weight, height, width, depth);
        b.setAvailable(isAvailable);
        if (!message.isEmpty()) {
            throw new BuildException(message);
        }
        return b;
    }

    private String bookDataValidation(String idProduct, String name, String description, double price, int stock, String isbn, String title, String author, String publisher, int publishYear,
            double weight, double height, double width, double depth) throws BuildException {
        String errorMessage = "";
        //validacion de los campos del producto
        errorMessage += productDataValidation(idProduct, name, description, price, stock);
        //validacion de los campos del libro
        if (setIsbn(isbn) != 0) {
            errorMessage += "ISBN incorrecto. ";
        }
        if (setTitle(title) != 0) {
            errorMessage += "Titulo incorrecto. ";
        }
        if (setAuthor(author) != 0) {
            errorMessage += "Autor incorrecto. ";
        }
        if (setPublisher(publisher) != 0) {
            errorMessage += "Editorial incorrecta. ";
        }
        if (setPublishYear(publishYear) != 0) {
            errorMessage += "Año de publicacion incorrecto. ";
        }

        //creacion del physical data
        PhysicalData pd = PhysicalData.getInstance(weight, height, width, depth);
        if (pd == null) {
            errorMessage += "Datos físicos incorrectos. ";
        } else {
            this.physicalData = pd;
        }

        return errorMessage;
    }

    public String getIsbn() {
        return isbn;
    }

    public int setIsbn(String isbn) {
        if (isbn != null) {
            this.isbn = isbn;
            return 0;
        }
        return -1;
    }

    public String getTitle() {
        return title;
    }

    public int setTitle(String title) {
        if (Check.minStringChars(title, 1)) {
            this.title = title;
            return 0;
        }
        return -1;
    }

    public String getAuthor() {
        return author;
    }

    public int setAuthor(String author) {
        if (Check.minStringChars(author, 1)) {
            this.author = author;
            return 0;
        }
        return -1;
    }

    public String getPublisher() {
        return publisher;
    }

    public int setPublisher(String publisher) {
        if (Check.minStringChars(publisher, 2)) {
            this.publisher = publisher;
            return 0;
        }
        return -1;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public int setPublishYear(int publishYear) {
        if (Check.isValidNumber(publishYear, 0)) {
            this.publishYear = publishYear;
            return 0;
        }
        return -1;
    }

    @Override
    public boolean isAvailable() {
        return this.isAvailable;
    }

    @Override
    public double getVolume() {
        return physicalData.getVolume();
    }

    @Override
    public double getArea() {
        return physicalData.getArea();
    }

    public PhysicalData getPhysicalData() {
        return physicalData;
    }

}
