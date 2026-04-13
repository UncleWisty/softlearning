// package com.example.softlearning.functionals;

// import com.example.appservices.serializers.JacksonSerializer;
// import com.example.appservices.serializers.Serializer;
// import com.example.core.entities.client.dto.ClientDTO;
// import com.example.core.entities.client.dto.SpanishClientDTO;

// public class T3SerializersWithAnnotations {

//     public static void main(String[] args) {
//         ClientDTO c = new ClientDTO("1001", "john@gmail.com", "987857467", "C/izan 15", "John", "20/05/2025 00:00:00", 1002);
//         SpanishClientDTO sc = new SpanishClientDTO("3043", "pepe@gmail.com", "938475647", "C/cojones 15", "Pepe", "10/07/2025 00:00:00", 1005);

//         try {
//             //CLIENT
//             Serializer formatter = new JacksonSerializer<ClientDTO>();
//             System.out.println("-----------CLIENT JSON------------");
//             String jclient = formatter.serialize(c);
//             System.out.println(jclient);

//             System.out.println("-----------CLIENT OBJECT------------");
//             ClientDTO clientDTO = (ClientDTO) formatter.deserialize(jclient, ClientDTO.class);
//             System.out.println(clientDTO);

//             System.out.println("\n|||||||||||||||||||||||||||||||||||||||||||||||||||||\n");

//             //SPANISH CLIENT
//             formatter = new JacksonSerializer<SpanishClientDTO>();

//             System.out.println("-----------SPANISH CLIENT JSON------------");
//             String jsclient = formatter.serialize(sc);
//             System.out.println(jsclient);

//             System.out.println("-----------SPANISH CLIENT OBJECTs------------");
//             SpanishClientDTO SpanishClientDTO = (SpanishClientDTO) formatter.deserialize(jsclient, SpanishClientDTO.class);
//             System.out.println(SpanishClientDTO);

//         } catch (Exception e) {
//             System.out.println(e.getMessage());
//         }
//     }
// }
