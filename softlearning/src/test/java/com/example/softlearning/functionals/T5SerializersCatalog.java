// package com.example.softlearning.functionals;

// import com.example.appservices.serializers.Serializer;
// import com.example.appservices.serializers.Serializers;
// import com.example.appservices.serializers.SerializersCatalog;
// import com.example.core.entities.client.dto.ClientDTO;
// import com.example.core.entities.client.dto.SpanishClientDTO;

// public class T5SerializersCatalog {

//     public static void main(String[] args) {
//         ClientDTO c = new ClientDTO("1001", "john@gmail.com", "987857467", "C/izan 15", "John", "20/05/2025 00:00:00", 1002);
//         SpanishClientDTO sc = new SpanishClientDTO("3043", "pepe@gmail.com", "938475647", "C/cojones 15", "Pepe", "10/07/2025 00:00:00", 1005);

//         try {
//             Serializer formatter = SerializersCatalog.getInstance(Serializers.JSON_CLIENT);
//             String jclient = formatter.serialize(c);
//             System.out.println(jclient);

//             ClientDTO clientDTO = (ClientDTO) formatter.deserialize(jclient, ClientDTO.class);
//             System.out.println(clientDTO);

//             formatter = SerializersCatalog.getInstance(Serializers.JSON_SP_CLIENT);
//             String jsclient = formatter.serialize(sc);
//             System.out.println(jsclient);

//             SpanishClientDTO SpanishClientDTO = (SpanishClientDTO) formatter.deserialize(jsclient, SpanishClientDTO.class);
//             System.out.println(SpanishClientDTO);

//             formatter = SerializersCatalog.getInstance(Serializers.XML_CLIENT);
//             String xmlClient = formatter.serialize(c);
//             System.out.println(xmlClient);

//             clientDTO = (ClientDTO) formatter.deserialize(xmlClient, ClientDTO.class);
//             System.out.println(clientDTO);

//             formatter = SerializersCatalog.getInstance(Serializers.XML_SP_CLIENT);
//             String xmlsclient = formatter.serialize(sc);
//             System.out.println(xmlsclient);

//             SpanishClientDTO = (SpanishClientDTO) formatter.deserialize(xmlsclient, SpanishClientDTO.class);
//             System.out.println(SpanishClientDTO);

//         } catch (Exception e) {
//             System.out.println(e.getMessage());
//         }
//     }
// }
