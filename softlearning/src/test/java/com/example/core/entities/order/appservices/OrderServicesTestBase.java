package com.example.core.entities.order.appservices;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import static org.mockito.Mockito.mockStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.core.entities.order.dto.OrderDTO;
import com.example.core.entities.order.persistence.OrderRepository;
import com.example.services.serializers.Serializer;
import com.example.services.serializers.SerializersCatalog;

@ExtendWith(MockitoExtension.class)
public class OrderServicesTestBase {

    @Mock
    protected OrderRepository orderRepository;

    @Mock
    protected Serializer<OrderDTO> serializer;

    @Mock
    protected Serializer<OrderDTO> xmlSerializer;

    @Mock
    protected Serializer<java.util.List<OrderDTO>> listSerializer;

    protected TestOrderServicesImpl orderServices;

    protected static MockedStatic<SerializersCatalog> mockedStatic = mockStatic(SerializersCatalog.class);

    protected void setUp() {
        orderServices = new TestOrderServicesImpl();
        try {
            java.lang.reflect.Field repoField = OrderServicesImpl.class.getDeclaredField("orderRepository");
            repoField.setAccessible(true);
            repoField.set(orderServices, orderRepository);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected static class TestOrderServicesImpl extends OrderServicesImpl {

        public void setParentSerializer(Serializer<OrderDTO> ser) {
            try {
                java.lang.reflect.Field serField = OrderServicesImpl.class.getDeclaredField("serializer");
                serField.setAccessible(true);
                serField.set(this, ser);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}