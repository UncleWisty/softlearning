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

import com.example.core.entities.book.appservices.BookServices;
import com.example.shared.exceptions.ServiceException;


/*
BOOK (JSON)
Headers: Content-Type: application/json
Body (raw JSON):
{
  "idProduct": "BK001",
  "name": "Effective Java",
  "description": "Guía esencial sobre Java",
  "price": 45.0,
  "stock": 10,
  "isAvailable": true,
  "isbn": "9780134685991",
  "title": "Effective Java",
  "author": "Joshua Bloch",
  "publisher": "Addison-Wesley",
  "publishYear": 2018,
  "weight": 0.8,
  "height": 23.0,
  "width": 15.0,
  "depth": 3.0
}

BOOK (XML)
Headers: Content-Type: application/xml
Body (raw XML):
<book>
  <idProduct>BK001</idProduct>
  <name>Effective Java</name>
  <description>Guía esencial sobre Java</description>
  <price>45.0</price>
  <stock>10</stock>
  <isAvailable>true</isAvailable>
  <isbn>9780134685991</isbn>
  <title>Effective Java</title>
  <author>Joshua Bloch</author>
  <publisher>Addison-Wesley</publisher>
  <publishYear>2018</publishYear>
  <weight>0.8</weight>
  <height>23.0</height>
  <width>15.0</width>
  <depth>3.0</depth>
</book>
 */
@RestController
@RequestMapping("/softlearning/books")
public class RestBookController {

    @Autowired
    BookServices bookServices;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllBooksJson() {
        try {
            return ResponseEntity.ok(bookServices.getAllBooksToJson());
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getJsonBookByIdProduct(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok(bookServices.getByIdProductToJson(id));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getXmlBookByIdProduct(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok(bookServices.getByIdProductToXml(id));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> newBookFromJson(@RequestBody String bookdata) {
        try {
            return ResponseEntity.ok(bookServices.addFromJson(bookdata));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> newBookFromXml(@RequestBody String bookdata) {
        try {
            return ResponseEntity.ok(bookServices.addFromXml(bookdata));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateBookFromJson(@PathVariable(value = "id") String id,
            @RequestBody String bookdata) {
        try {
            return ResponseEntity.ok(bookServices.updateOneFromJson(bookdata));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> updateBookFromXml(@PathVariable(value = "id") String id,
            @RequestBody String bookdata) {
        try {
            return ResponseEntity.ok(bookServices.updateOneFromXml(bookdata));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByID(@PathVariable(value = "id") String id) {
        try {
            bookServices.deleteByIdProduct(id);
            return ResponseEntity.ok().build();
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
