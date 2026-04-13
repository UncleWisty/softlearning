package com.example.core.entities.order.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.example.shared.exceptions.BuildException;

public class OrderDetailTest {

    @Test
    void testConstructorAndGetters() {
        OrderDetail detail = new OrderDetail("ref1", 10.0, 2.0, 5);

        assertEquals("ref1", detail.getRef());
        assertEquals(10.0, detail.getPrice());
        assertEquals(2.0, detail.getDiscount());
        assertEquals(5, detail.getAmount());
    }

    @Test
    void testSetters() {
        OrderDetail detail = new OrderDetail("ref1", 10.0, 2.0, 5);

        detail.setRef("ref2");
        detail.setPrice(15.0);
        detail.setDiscount(3.0);

        assertEquals("ref2", detail.getRef());
        assertEquals(15.0, detail.getPrice());
        assertEquals(3.0, detail.getDiscount());
    }

    @Test
    void testSetAmountValid() throws BuildException {
        OrderDetail detail = new OrderDetail("ref1", 10.0, 2.0, 5);

        detail.setAmount(10);

        assertEquals(10, detail.getAmount());
    }

    @Test
    void testSetAmountInvalid() {
        OrderDetail detail = new OrderDetail("ref1", 10.0, 2.0, 5);

        BuildException exception = assertThrows(BuildException.class, () -> detail.setAmount(-1));
        assertEquals("amount cannot be < 0", exception.getMessage());
    }
}
