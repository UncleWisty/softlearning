// package com.example.softlearning.functionals;

// import com.example.appservices.serializers.JacksonSerializer;
// import com.example.appservices.serializers.Serializer;
// import com.example.appservices.serializers.XmlJacksonSerializer;
// import com.example.core.entities.client.dto.ClientDTO;
// import com.example.core.entities.client.dto.SpanishClientDTO;

// public class T4JsonAndXmlSerializers {

//     public static void main(String[] args) {
//         ClientDTO c = new ClientDTO("1001", "john@gmail.com", "987857467", "C/izan 15", "John", "20/05/2025 00:00:00", 1002);
//         SpanishClientDTO sc = new SpanishClientDTO("3043", "pepe@gmail.com", "938475647", "C/cojones 15", "Pepe", "10/07/2025 00:00:00", 1005);

//         try {

//             //CLIENT
//             Serializer formatter = new JacksonSerializer<ClientDTO>();

//             System.out.println("--------JSON CLIENT-------");
//             String jclient = formatter.serialize(c);
//             System.out.println(jclient);

//             System.out.println("--------OBJECT CLIENT-------");
//             ClientDTO clientDTO = (ClientDTO) formatter.deserialize(jclient, ClientDTO.class);
//             System.out.println(clientDTO);

//             //SPANISH CLIENT
//             formatter = new JacksonSerializer<SpanishClientDTO>();

//             System.out.println("--------JSON SPANISH CLIENT-------");
//             String jsclient = formatter.serialize(sc);
//             System.out.println(jsclient);

//             System.out.println("--------OBJECT SPANISH CLIENT-------");
//             SpanishClientDTO SpanishClientDTO = (SpanishClientDTO) formatter.deserialize(jsclient, SpanishClientDTO.class);
//             System.out.println(SpanishClientDTO);

//             //XML CLIENT
//             formatter = new XmlJacksonSerializer<ClientDTO>();

//             System.out.println("--------XML CLIENT-------");
//             String xmlClient = formatter.serialize(c);
//             System.out.println(xmlClient);

//             System.out.println("--------OBJECT CLIENT-------");
//             clientDTO = (ClientDTO) formatter.deserialize(xmlClient, ClientDTO.class);
//             System.out.println(clientDTO);

//             //XML SPANISH CLIENT
//             formatter = new XmlJacksonSerializer<SpanishClientDTO>();

//             System.out.println("--------XML SPANISH CLIENT-------");
//             String xmlsclient = formatter.serialize(sc);
//             System.out.println(xmlsclient);

//             System.out.println("--------OBJECT SPANISH CLIENT-------");
//             SpanishClientDTO = (SpanishClientDTO) formatter.deserialize(xmlsclient, SpanishClientDTO.class);
//             System.out.println(SpanishClientDTO);

//         } catch (Exception e) {
//             System.out.println(e.getMessage());
//         }
//     }
// }
