package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.core.entities.vehicle.dto.VehicleDTO;
import com.example.core.entities.vehicle.persistence.VehicleRepository;

@SpringBootApplication
public class SoftlearningApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SoftlearningApplication.class, args);

        // System.out.println("TEST VEHICLES");
        // var repo = context.getBean(VehicleRepository.class);
        // System.out.println("\n *****   AÑADIENDO UN NUEVO VEHICULO  ***** \n");
        // repo.save(new VehicleDTO("1234ABC", "Honda", "Hybrid","Comodo, economico", 700, 5045.9, "02-01-2026, 10:35:00", "14-12-2027, 19:30:00"));
        // System.out.println("\n *****   actualizando vehiculo  ***** \n");
        // repo.save(new VehicleDTO("1234ABC", "Prueba", "Pruebilla","Ancho, prueba", 200, 1273.9, "02-01-2026, 10:35:00", "14-12-2027, 19:30:00"));
        // System.out.println("\n *****   vehiculo:   ***** \n");
        // repo.findByMatricula("1234ABC").ifPresent(System.out::println);
        // // System.out.println("\n *****   eliminando vehiculo  ***** \n");
        // // repo.deleteByMatricula("1234ABC");
        // System.out.println("\n *****   vehiculo:    ***** \n");
        // repo.findByMatricula("1234ABC").ifPresent(System.out::println);
        





        System.out.println("\n*****   A P P L I C A T I O N    S T A R T E D   *****\n");

    }



@Bean
public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:5173")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .allowCredentials(true);
        }
    };
}

//     public static void main(String[] args) {
//         ApplicationContext context = SpringApplication.run(SoftlearningApplication.class, args);
//         //BOOKS ========================================================================================================================
//         System.out.println("Printing all books with BookRepository");
//         var repo = context.getBean(BookRepository.class);
//         System.out.println("\n *****   Java Books by title  ***** \n");
//         repo.findByTitle("java").forEach(System.out::println);
//         System.out.println("\n *****   Add a new Java Book  ***** \n");
//         repo.save(new BookDTO("9999", "Java SpringJPA", "Some description", 29.99, 1, true,
//                 "isbnproves4", "Java SpringJPA", "Princeton", "Programmers", 2024, 450.0, 21.0, 14.0, 2.5));
//         System.out.println("\n *****   Java Books by partial title  ***** \n");
//         repo.findByPartialTitle("java").forEach(System.out::println);
//         System.out.println("\n *****   Update a Java Book  ***** \n");
//         repo.save(new BookDTO("9999", "SpringJPA", "Some description", 29.99, 1, true,
//                 "isbnproves4", "SpringJPA", "Princeton", "Programmers", 2024, 450.0, 21.0, 14.0, 2.5));
//         System.out.println("\n *****   Books by id   ***** \n");
//         repo.findByIdProduct("9999").ifPresent(System.out::println);
//         // System.out.println("\n *****   Delete a Book  ***** \n");
//         // repo.deleteByIdProduct("9999");
//         System.out.println("\n *****   Books by id    ***** \n");
//         repo.findByIdProduct("9999").ifPresent(System.out::println);
//         System.out.println("\n *****    Java Books available: " + repo.countByPartialTitle("Java"));
//         //CLIENTS ========================================================================================================================
//         System.out.println("\nPrinting all clients with ClientRepository");
//         var clientRepo = context.getBean(JpaClientRepository.class);
//         System.out.println("\n *****   Clients in the repository   ***** \n");
//         clientRepo.findAll().forEach(System.out::println);
//         System.out.println("\n *****   Clients by name  ***** \n");
//         clientRepo.findByNamePerson("John").forEach(System.out::println);
//         System.out.println("\n *****   Add a new Client  ***** \n");
//         clientRepo.save(new ClientDTO("person123", "john@example.com", "123-456-7890", "123 Main St", "John Doe", "2023-01-01", 1));
//         System.out.println("\n *****   Clients by partial name  ***** \n");
//         clientRepo.findByPartialName("John").forEach(System.out::println);
//         System.out.println("\n *****   Update a Client  ***** \n");
//         clientRepo.save(new ClientDTO("person123", "john.doe@example.com", "123-456-7890", "123 Main St", "John Doe", "2023-01-01", 1));
//         System.out.println("\n *****   Clients by id   ***** \n");
//         clientRepo.findById(1).ifPresent(System.out::println);
//         // System.out.println("\n *****   Delete a Client  ***** \n");
//         // clientRepo.deleteById(1);
//         System.out.println("\n *****   Clients by id    ***** \n");
//         clientRepo.findById(1).ifPresent(System.out::println);
//         System.out.println("\n *****    Clients with name like John: " + clientRepo.countByPartialName("John"));
//         //A P P S E R V I C E S =====================================================================
//         //===========================================================================================
//         //
//         //BOOK SERVICES ==========================================================================
//         System.out.println("\n *****   B O O K   A P P S E R V I C E S   ***** \n");
//         System.out.println("\n *****   AppServices Books_by_id   ***** \n");
//         var bookServices = context.getBean(BookServicesImpl.class);
//         try {
//             System.out.println("\n *****   JSON DOCUMENT   ***** \n");
//             System.out.println(bookServices.getByIdProductToJson("9999"));
//             System.out.println("\n *****   XML DOCUMENT   ***** \n");
//             System.out.println(bookServices.getByIdProductToXml("9999"));
//             System.out.println("\n *****   ADD FROM JSON   ***** \n");
//             String newBookJson = "{\"idProduct\":\"1234\",\"name\":\"New Java Book\",\"description\":\"A new comprehensive Java book\",\"price\":39.99,\"stock\":10,\"isAvailable\":true,\"isbn\":\"9780451524935\",\"title\":\"New Java Book\",\"publisher\":\"Tech Press\",\"author\":\"Tech Author\",\"publishYear\":2024,\"weight\":500.0,\"width\":21.0,\"height\":14.0,\"depth\":2.5}";
//             System.out.println(bookServices.addFromJson(newBookJson));
//             System.out.println("\n *****   ADD FROM XML   ***** \n");
//             String newBookXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><BookDTO><idProduct>4321</idProduct><name>XML Book</name><description>Book from XML with comprehensive details</description><price>49.99</price><stock>5</stock><isAvailable>true</isAvailable><isbn>9780060935467</isbn><title>XML Book</title><publisher>XML Press</publisher><author>XML Author</author><publishYear>2025</publishYear><weight>400.0</weight><width>20.0</width><height>15.0</height><depth>3.0</depth></BookDTO>";
//             System.out.println(bookServices.addFromXml(newBookXml));
//             System.out.println("\n *****   UPDATE FROM JSON   ***** \n");
//             String updateBookJson = "{\"idProduct\":\"1234\",\"name\":\"Updated Book\",\"description\":\"Book has been updated with new information\",\"price\":59.99,\"stock\":20,\"isAvailable\":true,\"isbn\":\"9780141019079\",\"title\":\"Updated Book\",\"publisher\":\"Updated Press\",\"author\":\"Updated Author\",\"publishYear\":2025,\"weight\":600.0,\"width\":22.0,\"height\":16.0,\"depth\":3.5}";
//             System.out.println(bookServices.updateOneFromJson(updateBookJson));
//             System.out.println("\n *****   UPDATE FROM XML   ***** \n");
//             String updateBookXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><BookDTO><idProduct>4321</idProduct><name>Updated from XML</name><description>Updated via XML with detailed information</description><price>69.99</price><stock>15</stock><isAvailable>true</isAvailable><isbn>9780451524935</isbn><title>Updated from XML</title><publisher>XML Update Press</publisher><author>XML Update Author</author><publishYear>2025</publishYear><weight>550.0</weight><width>21.5</width><height>15.5</height><depth>3.0</depth></BookDTO>";
//             System.out.println(bookServices.updateOneFromXml(updateBookXml));
//             System.out.println("\n *****   DELETE   ***** \n");
//             bookServices.deleteByIdProduct("1234");
//             bookServices.deleteByIdProduct("4321");
//             System.out.println("\n *****   DONE   ***** \n");
//         } catch (ServiceException e) {
//             System.out.println("\n - - - - " + e.getMessage() + " - - - - \n");
//         }
//         //CLIENT SERVICES ==========================================================================
//         System.out.println("\n *****   C L I E N T   A P P S E R V I C E S   ***** \n");
//         System.out.println("\n *****   AppServices Clients_by_id   ***** \n");
//         var clientServices = context.getBean(ClientServicesImpl.class);
//         try {
//             System.out.println("\n *****   JSON DOCUMENT   ***** \n");
//             System.out.println(clientServices.getByIdClientToJson(1));
//             System.out.println("\n *****   XML DOCUMENT   ***** \n");
//             System.out.println(clientServices.getByIdClientToXml(1));
//             System.out.println("\n *****   ADD FROM JSON   ***** \n");
//             String newClientJson = "{\"idClient\":9999,\"idPerson\":\"newclient123\",\"email\":\"newclient@example.com\",\"phone\":\"555-123-4567\",\"adress\":\"123 New Street\",\"namePerson\":\"New Client Person\",\"registrationDate\":\"20-01-2026, 10:30:00\"}";
//             System.out.println(clientServices.addFromJson(newClientJson));
//             System.out.println("\n *****   ADD FROM XML   ***** \n");
//             String newClientXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ClientDTO><idClient>8888</idClient><idPerson>xmlclient456</idPerson><email>xmlclient@example.com</email><phone>555-567-8901</phone><adress>456 XML Avenue</adress><namePerson>XML Client Person</namePerson><registrationDate>20-01-2026, 14:45:00</registrationDate></ClientDTO>";
//             System.out.println(clientServices.addFromXml(newClientXml));
//             System.out.println("\n *****   UPDATE FROM JSON   ***** \n");
//             String updateClientJson = "{\"idClient\":9999,\"idPerson\":\"updatedperson\",\"email\":\"updated@example.com\",\"phone\":\"555-999-9999\",\"adress\":\"999 Updated Lane\",\"namePerson\":\"Updated Client\",\"registrationDate\":\"20-01-2026, 15:30:00\"}";
//             System.out.println(clientServices.updateOneFromJson(updateClientJson));
//             System.out.println("\n *****   UPDATE FROM XML   ***** \n");
//             String updateClientXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ClientDTO><idClient>8888</idClient><idPerson>xmlupdated789</idPerson><email>xmlupdated@example.com</email><phone>555-789-0123</phone><adress>789 XML Updated Way</adress><namePerson>XML Updated Client</namePerson><registrationDate>20-01-2026, 16:15:00</registrationDate></ClientDTO>";
//             System.out.println(clientServices.updateOneFromXml(updateClientXml));
//             System.out.println("\n *****   DELETE   ***** \n");
//             clientServices.deleteByIdClient(9999);
//             clientServices.deleteByIdClient(8888);
//             System.out.println("\n *****   DONE   ***** \n");
//         } catch (ServiceException e) {
//             System.out.println("\n - - - - " + e.getMessage() + " - - - - \n");
//         }
//     }
}
