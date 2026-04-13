// package com.example.softlearning.functionals;

// import com.example.appservices.serializers.book.BookJsonSerializer;
// import com.example.core.entities.book.dto.BookDTO;

// public class T1SpecificJsonSerializers {

//     public static void main(String[] args) {
//         BookDTO b = new BookDTO("123-4567890123", "Effective Java", "A programming book", 45.0, 10, true,
//                 "123-4567890123", "Effective Java", "Joshua Bloch", "Addison-Wesley", 2018,
//                 1.2, 9.0, 6.0, 1.5);
//         try {
//             System.out.println("--------JSON BOOK----------");
//             String jbook = BookJsonSerializer.serialize(b);
//             System.out.println(jbook);

//             System.out.println("--------OBJECT BOOK----------");

//             BookDTO copyDTO = BookJsonSerializer.deserialize(jbook);
//             System.out.println(copyDTO);
//         } catch (Exception e) {
//             System.out.println(e.getMessage());
//         }

//     }

// }
