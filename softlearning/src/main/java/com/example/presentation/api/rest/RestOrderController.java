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

import com.example.core.entities.order.appservices.OrderServices;
import com.example.shared.exceptions.ServiceException;

@RestController
@RequestMapping("/softlearning/orders")
public class RestOrderController {

    @Autowired
    OrderServices orderServices;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllOrdersJson() {
        try {
            return ResponseEntity.ok(orderServices.getAllOrdersToJson());
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getJsonOrderById(@PathVariable(value = "id") int id) {
        try {
            return ResponseEntity.ok(orderServices.getByOrderIDToJson(id));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getXmlOrderById(@PathVariable(value = "id") int id) {
        try {
            return ResponseEntity.ok(orderServices.getByOrderIDToXml(id));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> newOrderFromJson(@RequestBody String orderData) {
        try {
            return ResponseEntity.ok(orderServices.addFromJson(orderData));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> newOrderFromXml(@RequestBody String orderData) {
        try {
            return ResponseEntity.ok(orderServices.addFromXml(orderData));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateOrderFromJson(@PathVariable(value = "id") int id,
            @RequestBody String orderData) {
        try {
            return ResponseEntity.ok(orderServices.updateOneFromJson(orderData));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> updateOrderFromXml(@PathVariable(value = "id") int id,
            @RequestBody String orderData) {
        try {
            return ResponseEntity.ok(orderServices.updateOneFromXml(orderData));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByID(@PathVariable(value = "id") int id) {
        try {
            orderServices.deleteByOrderID(id);
            return ResponseEntity.ok().build();
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}