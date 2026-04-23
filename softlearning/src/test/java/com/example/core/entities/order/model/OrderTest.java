package com.example.core.entities.order.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.example.shared.exceptions.BuildException;
import com.example.shared.exceptions.GeneralDateTimeException;

public class OrderTest {

    @Test
    void testConstructor() {
        Order order = new Order(1, 100);

        assertNotNull(order.getShopCart());
        assertEquals(0, order.numDetails());
    }

    @Test
    void testGetInstanceValid() throws BuildException, GeneralDateTimeException {
        Order order = Order.getInstance(1, 100, "01/01/2023-00:00:00", "Test order");

        assertNotNull(order);
        assertEquals(1, order.getRef());
        assertEquals("01/01/2023-00:00:00", order.getStartDate());
        assertEquals("Test order", order.getDescription());
    }

    @Test
    void testGetInstanceInvalid() {
        assertThrows(BuildException.class, () -> Order.getInstance(1, 100, "invalid-date", "Test"));
    }

    @Test
    void testAddDetail() {
        Order order = new Order(1, 100);

        int result = order.addDetail("ref1", 10.0, 2.0, 5);

        assertEquals(0, result);
        assertEquals(1, order.numDetails());
        assertEquals("ref1,10.0,2.0,5", order.getDetail(0));
    }

    @Test
    void testGetDetails() {
        Order order = new Order(1, 100);
        order.addDetail("ref1", 10.0, 2.0, 5);
        order.addDetail("ref2", 15.0, 1.0, 3);

        String details = order.getDetails();

        assertEquals("ref1,10.0,2.0,5;ref2,15.0,1.0,3;", details);
    }

    @Test
    void testGetDetailNice() {
        Order order = new Order(1, 100);
        order.addDetail("ref1", 10.0, 2.0, 5);

        String nice = order.getDetailNice(0);

        assertEquals("Referencia: ref1 Precio: 10.0 Descuento: 2.0 Cantidad: 5", nice);
    }

    @Test
    void testSetDimensions() throws BuildException {
        Order order = new Order(1, 100);

        order.setDimensions("1.0,2.0,3.0,4.0");

        assertNotNull(order.orderPackage);
        assertEquals(1.0, order.orderPackage.getWeight());
        assertEquals(2.0, order.orderPackage.getHeight());
        assertEquals(3.0, order.orderPackage.getWidth());
        assertEquals(4.0, order.orderPackage.getDepth());
    }

    @Test
    void testSetWeight() throws BuildException {
        Order order = new Order(1, 100);
        order.setDimensions("1.0,2.0,3.0,4.0");

        int result = order.setWeight(5.0);

        assertEquals(0, result);
        assertEquals(5.0, order.orderPackage.getWeight());
    }

    @Test
    void testSetHeight() throws BuildException {
        Order order = new Order(1, 100);
        order.setDimensions("1.0,2.0,3.0,4.0");

        int result = order.setHeight(6.0);

        assertEquals(0, result);
        assertEquals(6.0, order.orderPackage.getHeight());
    }

    @Test
    void testSetWidth() throws BuildException {
        Order order = new Order(1, 100);
        order.setDimensions("1.0,2.0,3.0,4.0");

        int result = order.setWidth(7.0);

        assertEquals(0, result);
        assertEquals(7.0, order.orderPackage.getWidth());
    }

    @Test
    void testSetDepth() throws BuildException {
        Order order = new Order(1, 100);
        order.setDimensions("1.0,2.0,3.0,4.0");

        int result = order.setDepth(8.0);

        assertEquals(0, result);
        assertEquals(8.0, order.orderPackage.getDepth());
    }

    @Test
    void testSetDimensionsInvalidCsv() {
        Order order = new Order(1, 100);

        assertThrows(BuildException.class, () -> order.setDimensions("1.0,2.0,3.0"));
    }

    @Test
    void testUpdateShopCartDetailsAndTotalPrice() {
        Order order = new Order(1, 100);
        order.addDetail("ref1", 10.0, 2.0, 5);
        order.addDetail("ref2", 15.0, 1.0, 3);

        int updateResult = order.updateShopCartDetails("ref2", 4);

        assertEquals(0, updateResult);
        assertEquals("ref2,15.0,1.0,4", order.getDetail(1));
        assertEquals(96.0, order.getTotalPrice());
    }

    @Test
    void testSetFinishDateInvalidStatus() {
        Order order = new Order(1, 100);

        assertThrows(GeneralDateTimeException.class, () -> order.setFinishDate("02/01/2023-00:00:00"));
    }

    @Test
    void testSetFinishDateWhenDelivered() throws Exception {
        Order order = new Order(1, 100);
        order.setPaymentDate("01/01/2023-00:00:00");
        order.setStatus(OrderStatus.FORTHCOMING);
        order.setDeliveryDate("02/01/2023-00:00:00");

        order.setFinishDate("03/01/2023-00:00:00");

        assertEquals(OrderStatus.FINISHED.name(), order.getStatus());
        assertNotNull(order.getFinishDate());
    }
}
