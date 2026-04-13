// package com.example.softlearning.functionals;

// import java.util.List;
// import java.util.Set;

// import com.example.appservices.serializers.Serializer;
// import com.example.appservices.serializers.Serializers;
// import com.example.appservices.serializers.SerializersCatalog;
// import com.example.core.entities.book.dto.BookDTO;
// import com.example.core.entities.client.dto.ClientDTO;
// import com.example.core.entities.client.dto.SpanishClientDTO;
// import com.example.core.entities.order.services.OrderDTO;
// import com.example.core.entities.order.services.OrderDetailDTO;
// import com.example.core.entities.order.services.SpanishOrderDTO;

// public class SerializersTest {

//     public static void main(String[] args) {
//         //BOOKS
//         System.out.println("\n================= BOOKS =================\n\n");

//         BookDTO b = new BookDTO("1001", "The Great Gatsby", "The great book from F. Scott Fitzgerald", 19.99, 50, true, "9780743273565", "The Great Gatsby", "F. Scott Fitzgerald", "Scribner", 1925, 0.5, 8.0, 5.0, 1.0);

//         try {
//             //ENGLISH BOOKS
//             System.out.println("------------- JSON BOOK -------------");
//             Serializer formatter = SerializersCatalog.getInstance(Serializers.JSON_BOOK);
//             String jbook = formatter.serialize(b);
//             System.out.println(jbook);

//             BookDTO deserializedBook = (BookDTO) formatter.deserialize(jbook, BookDTO.class);
//             System.out.println("\n===\n");
//             System.out.println(deserializedBook);

//             System.out.println("------------- XML BOOK -------------");
//             formatter = SerializersCatalog.getInstance(Serializers.XML_BOOK);
//             String xbook = formatter.serialize(b);
//             System.out.println(xbook);

//             deserializedBook = (BookDTO) formatter.deserialize(xbook, BookDTO.class);
//             System.out.println("\n===\n");
//             System.out.println(deserializedBook);

//             //SPANISH BOOKS
//             System.out.println("------------- JSON SPANISH BOOK -------------");
//             formatter = SerializersCatalog.getInstance(Serializers.JSON_SP_BOOK);
//             String jsbook = formatter.serialize(b);
//             System.out.println(jsbook);

//             deserializedBook = (BookDTO) formatter.deserialize(jsbook, BookDTO.class);
//             System.out.println("\n===\n");
//             System.out.println(deserializedBook);

//             System.out.println("------------- XML SPANISH BOOK -------------");
//             formatter = SerializersCatalog.getInstance(Serializers.XML_SP_BOOK);
//             String xsbook = formatter.serialize(b);
//             System.out.println(xsbook);

//             deserializedBook = (BookDTO) formatter.deserialize(xsbook, BookDTO.class);
//             System.out.println("\n===\n");
//             System.out.println(deserializedBook);

//         } catch (Exception e) {
//             System.out.println(e.getMessage());
//         }

//         //CLIENTS
//         System.out.println("\n================= CLIENTS =================\n\n");

//         ClientDTO c = new ClientDTO("1001", "john@gmail.com", "987857467", "C/izan 15", "John", "20/05/2025 00:00:00", 1002);
//         SpanishClientDTO sc = new SpanishClientDTO("3043", "pepe@gmail.com", "938475647", "C/cojones 15", "Pepe", "10/07/2025 00:00:00", 1005);

//         try {
//             //ENGLISH CLIENTS
//             System.out.println("------------- JSON CLIENT -------------");
//             Serializer formatter = SerializersCatalog.getInstance(Serializers.JSON_CLIENT);
//             String jclient = formatter.serialize(c);
//             System.out.println(jclient);

//             ClientDTO clientDTO = (ClientDTO) formatter.deserialize(jclient, ClientDTO.class);
//             System.out.println("\n===\n");
//             System.out.println(clientDTO);

//             System.out.println("------------- XML CLIENT -------------");
//             formatter = SerializersCatalog.getInstance(Serializers.XML_CLIENT);
//             String xmlClient = formatter.serialize(c);
//             System.out.println(xmlClient);

//             clientDTO = (ClientDTO) formatter.deserialize(xmlClient, ClientDTO.class);
//             System.out.println("\n===\n");
//             System.out.println(clientDTO);

//             //SPANISH CLIENTS
//             System.out.println("------------- JSON SPANISH CLIENT -------------");
//             formatter = SerializersCatalog.getInstance(Serializers.JSON_SP_CLIENT);
//             String jsclient = formatter.serialize(sc);
//             System.out.println(jsclient);

//             SpanishClientDTO SpanishClientDTO = (SpanishClientDTO) formatter.deserialize(jsclient, SpanishClientDTO.class);
//             System.out.println("\n===\n");
//             System.out.println(SpanishClientDTO);

//             System.out.println("------------- XML SPANISH CLIENT -------------");
//             formatter = SerializersCatalog.getInstance(Serializers.XML_SP_CLIENT);
//             String xmlsclient = formatter.serialize(sc);
//             System.out.println(xmlsclient);

//             SpanishClientDTO = (SpanishClientDTO) formatter.deserialize(xmlsclient, SpanishClientDTO.class);
//             System.out.println("\n===\n");
//             System.out.println(SpanishClientDTO);

//         } catch (Exception e) {
//             System.out.println(e.getMessage());
//         }

//         //ORDERS
//         System.out.println("\n================= ORDERS =================\n\n");

//         OrderDTO o = new OrderDTO(1, 1001, "C/Receiver 10", "Receiver Person", "2025-01-01", "2025-01-05", Set.of("123456789"), "pending", "2024-12-01", "Order description", "10x10x5", List.of(new OrderDetailDTO("REF001", 20.0, 2.0, 2)));
//         SpanishOrderDTO so = new SpanishOrderDTO(2, 1002, "C/Receptor 20", "Persona Receptor", "2025-02-01", "2025-02-05", Set.of("987654321"), "enviado", "2024-12-15", "Descripción del pedido", "15x15x10", List.of(new OrderDetailDTO("REF002", 30.0, 5.0, 1)));

//         try {
//             //ENGLISH ORDERS
//             System.out.println("------------- JSON ORDER -------------");
//             Serializer formatter = SerializersCatalog.getInstance(Serializers.JSON_ORDER);
//             String jorder = formatter.serialize(o);
//             System.out.println(jorder);

//             OrderDTO deserializedOrder = (OrderDTO) formatter.deserialize(jorder, OrderDTO.class);
//             System.out.println("\n===\n");
//             System.out.println(deserializedOrder);

//             System.out.println("------------- XML ORDER -------------");
//             formatter = SerializersCatalog.getInstance(Serializers.XML_ORDER);
//             String xorder = formatter.serialize(o);
//             System.out.println(xorder);

//             deserializedOrder = (OrderDTO) formatter.deserialize(xorder, OrderDTO.class);
//             System.out.println("\n===\n");
//             System.out.println(deserializedOrder);

//             //SPANISH ORDERS
//             System.out.println("------------- JSON SPANISH ORDER -------------");
//             formatter = SerializersCatalog.getInstance(Serializers.JSON_SP_ORDER);
//             String jsorder = formatter.serialize(so);
//             System.out.println(jsorder);

//             SpanishOrderDTO deserializedSpanishOrder = (SpanishOrderDTO) formatter.deserialize(jsorder, SpanishOrderDTO.class);
//             System.out.println("\n===\n");
//             System.out.println(deserializedSpanishOrder);

//             System.out.println("------------- XML SPANISH ORDER -------------");
//             formatter = SerializersCatalog.getInstance(Serializers.XML_SP_ORDER);
//             String xsorder = formatter.serialize(so);
//             System.out.println(xsorder);

//             deserializedSpanishOrder = (SpanishOrderDTO) formatter.deserialize(xsorder, SpanishOrderDTO.class);
//             System.out.println("\n===\n");
//             System.out.println(deserializedSpanishOrder);

//         } catch (Exception e) {
//             System.out.println(e.getMessage());
//         }
//     }
// }
