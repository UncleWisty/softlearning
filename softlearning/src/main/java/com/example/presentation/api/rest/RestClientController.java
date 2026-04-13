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

import com.example.core.entities.client.appservices.ClientServices;
import com.example.shared.exceptions.ServiceException;


/*
CLIENT (JSON)
Headers: Content-Type: application/json
Body (raw JSON):
{
  "idPerson": "12345678",
  "email": "juan.perez@example.com",
  "phone": "600123456",
  "adress": "Calle Falsa 123",
  "namePerson": "Juan Perez",
  "registrationDate": "01-02-2026, 12:00:00",
  "idClient": 1001
}

CLIENT (XML)
Headers: Content-Type: application/xml
Body (raw XML):
<client>
  <idPerson>12345678</idPerson>
  <email>juan.perez@example.com</email>
  <phone>600123456</phone>
  <adress>Calle Falsa 123</adress>
  <namePerson>Juan Perez</namePerson>
  <registrationDate>01-02-2026, 12:00:00</registrationDate>
  <idClient>1001</idClient>
</client>
 */
@RestController
@RequestMapping("/softlearning/clients")
public class RestClientController {

    @Autowired
    ClientServices clientServices;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllClientsJson() {
        try {
            return ResponseEntity.ok(clientServices.getAllClientsToJson());
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getJsonClientById(@PathVariable(value = "id") int id) {
        try {
            return ResponseEntity.ok(clientServices.getByIdClientToJson(id));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getXmlClientById(@PathVariable(value = "id") int id) {
        try {
            return ResponseEntity.ok(clientServices.getByIdClientToXml(id));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> newClientFromJson(@RequestBody String clientdata) {
        try {
            return ResponseEntity.ok(clientServices.addFromJson(clientdata));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> newClientFromXml(@RequestBody String clientdata) {
        try {
            return ResponseEntity.ok(clientServices.addFromXml(clientdata));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateClientFromJson(@PathVariable(value = "id") int id,
            @RequestBody String bookdata) {
        try {
            return ResponseEntity.ok(clientServices.updateOneFromJson(bookdata));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> updateClientFromXml(@PathVariable(value = "id") int id,
            @RequestBody String bookdata) {
        try {
            return ResponseEntity.ok(clientServices.updateOneFromXml(bookdata));
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByID(@PathVariable(value = "id") int id) {
        try {
            clientServices.deleteByIdClient(id);
            return ResponseEntity.ok().build();
        } catch (ServiceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
