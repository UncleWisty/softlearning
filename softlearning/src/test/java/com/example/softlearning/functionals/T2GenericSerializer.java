// package com.example.softlearning.functionals;

// import com.example.appservices.serializers.JacksonSerializer;
// import com.example.appservices.serializers.Serializer;
// import com.example.core.entities.book.dto.BookDTO;
// import com.example.core.entities.client.dto.ClientDTO;

// public class T2GenericSerializer {

//     public static void main(String[] args) {
//         BookDTO b = new BookDTO("123-4567890123", "Effective Java", "A programming book", 45.0, 10, true,
//                 "123-4567890123", "Effective Java", "Joshua Bloch", "Addison-Wesley", 2018,
//                 1.2, 9.0, 6.0, 1.5);
//         ClientDTO c = new ClientDTO("1001", "john@gmail.com", "987857467", "C/izan 15", "John", "20/05/2025 00:00:00", 1002);

//         try {

//             //BOOK
//             Serializer formatter = new JacksonSerializer<BookDTO>();

//             System.out.println("--------JSON BOOK----------");
//             String jbook = formatter.serialize(b);
//             System.out.println(jbook);

//             System.out.println("--------OBJECT BOOK----------");
//             BookDTO copyDTO = (BookDTO) formatter.deserialize(jbook, BookDTO.class);
//             System.out.println(copyDTO);

//             //CLIENT
//             formatter = new JacksonSerializer<ClientDTO>();

//             System.out.println("--------JSON CLIENT----------");
//             String jclient = formatter.serialize(c);
//             System.out.println(jclient);

//             System.out.println("--------OBJECT CLIENT----------");
//             ClientDTO clientDTO = (ClientDTO) formatter.deserialize(jclient, ClientDTO.class);
//             System.out.println(clientDTO);

//         } catch (Exception e) {
//             System.out.println(e.getMessage());
//         }
//     }
// }
