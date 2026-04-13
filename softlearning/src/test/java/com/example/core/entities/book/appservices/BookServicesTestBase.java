package com.example.core.entities.book.appservices;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import static org.mockito.Mockito.mockStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.core.entities.book.dto.BookDTO;
import com.example.core.entities.book.persistence.BookRepository;
import com.example.services.serializers.Serializer;
import com.example.services.serializers.SerializersCatalog;

@ExtendWith(MockitoExtension.class)
public class BookServicesTestBase {

    @Mock
    protected BookRepository bookRepository;

    @Mock
    protected Serializer<BookDTO> serializer;

    @Mock
    protected Serializer<BookDTO> xmlSerializer;

    @Mock
    protected Serializer<java.util.List<BookDTO>> listSerializer;

    protected TestBookServicesImpl bookServices;

    protected static MockedStatic<SerializersCatalog> mockedStatic = mockStatic(SerializersCatalog.class);

    protected void setUp() {
        bookServices = new TestBookServicesImpl();
        try {
            java.lang.reflect.Field repoField = BookServicesImpl.class.getDeclaredField("bookRepository");
            repoField.setAccessible(true);
            repoField.set(bookServices, bookRepository);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected static class TestBookServicesImpl extends BookServicesImpl {

        protected BookRepository bookRepository;
        protected Serializer<BookDTO> serializer;

        public void setParentSerializer(Serializer<BookDTO> ser) {
            try {
                java.lang.reflect.Field serField = BookServicesImpl.class.getDeclaredField("serializer");
                serField.setAccessible(true);
                serField.set(this, ser);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
