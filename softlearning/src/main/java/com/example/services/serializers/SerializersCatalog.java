package com.example.services.serializers;

import java.util.TreeMap;

import com.example.core.entities.book.dto.BookDTO;
import com.example.core.entities.book.dto.SpanishBookDTO;
import com.example.core.entities.client.dto.ClientDTO;
import com.example.core.entities.client.dto.SpanishClientDTO;
import com.example.core.entities.course.dto.CourseDTO;
import com.example.core.entities.order.dto.OrderDTO;
import com.example.core.entities.order.services.SpanishOrderDTO;
import com.example.core.entities.vehicle.dto.VehicleDTO;

public class SerializersCatalog {

    //para quitar el warning que me pone negro
    @SuppressWarnings("FieldMayBeFinal")
    private static TreeMap<Serializers, Serializer<?>> catalog = new TreeMap<>();

    private static void loadCatalog() {
        ///CLIENTS
        catalog.put(Serializers.JSON_CLIENT, new JacksonSerializer<ClientDTO>());
        catalog.put(Serializers.JSON_SP_CLIENT, new JacksonSerializer<SpanishClientDTO>());
        catalog.put(Serializers.XML_CLIENT, new XmlJacksonSerializer<ClientDTO>());
        catalog.put(Serializers.XML_SP_CLIENT, new XmlJacksonSerializer<SpanishClientDTO>());

        //BOOKS
        catalog.put(Serializers.JSON_BOOK, new JacksonSerializer<BookDTO>());
        catalog.put(Serializers.JSON_SP_BOOK, new JacksonSerializer<SpanishBookDTO>());
        catalog.put(Serializers.XML_BOOK, new XmlJacksonSerializer<BookDTO>());
        catalog.put(Serializers.XML_SP_BOOK, new XmlJacksonSerializer<SpanishBookDTO>());

        //ORDERS
        catalog.put(Serializers.JSON_ORDER, new JacksonSerializer<OrderDTO>());
        catalog.put(Serializers.XML_ORDER, new XmlJacksonSerializer<OrderDTO>());
        catalog.put(Serializers.JSON_SP_ORDER, new JacksonSerializer<SpanishOrderDTO>());
        catalog.put(Serializers.XML_SP_ORDER, new XmlJacksonSerializer<SpanishOrderDTO>());

        //COURSES
        catalog.put(Serializers.JSON_COURSE, new JacksonSerializer<CourseDTO>());

        //VEHICLES
        catalog.put(Serializers.JSON_VEHICLE, new JacksonSerializer<VehicleDTO>());
    }

    public static Serializer<?> getInstance(Serializers type) {
        if (catalog.isEmpty()) {
            loadCatalog();
        }
        return catalog.get(type);
    }
}
