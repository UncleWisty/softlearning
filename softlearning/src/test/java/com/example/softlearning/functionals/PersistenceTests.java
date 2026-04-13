package com.example.softlearning.functionals;

public class PersistenceTests {

    public static void main(String[] args) {
        /*
        
        ApplicationContext context = SpringApplication.run(PersistenceTests.class, args);

        System.out.println("Printing all books with BookRepository");

        var repo = context.getBean(JpaBookRepository.class);

        System.out.println("\n *****   Books in the repository   ***** \n");
        repo.findAll().forEach(System.out::println);

        System.out.println("\n *****   Java Books by title  ***** \n");
        repo.findByName("java").forEach(System.out::println);

        System.out.println("\n *****   Add a new Java Book  ***** \n");
        repo.save(new BookDTO("isbnproves4", "Java SpringJPA", "Some description", 29.99, 1, true,
                "isbnproves4", "Java SpringJPA", "Princeton", "Programmers", 2024, 450.0, 21.0, 14.0, 2.5));

        System.out.println("\n *****   Java Books by partial title  ***** \n");
        repo.findByPartialTitle("java").forEach(System.out::println);

        System.out.println("\n *****   Update a Java Book  ***** \n");
        repo.save(new BookDTO("isbnproves4", "SpringJPA", "Some description", 29.99, 1, true,
                "isbnproves4", "SpringJPA", "Princeton", "Programmers", 2024, 450.0, 21.0, 14.0, 2.5));

        System.out.println("\n *****   Books by id   ***** \n");
        repo.findById("137").ifPresent(System.out::println);

        System.out.println("\n *****   Delete a Book  ***** \n");
        repo.deleteById("137");
        System.out.println("\n *****   Books by id    ***** \n");
        repo.findById("137").ifPresent(System.out::println);

        System.out.println("\n *****    Java Books available: " + repo.countByPartialTitle("Java"));

         */
    }
}
