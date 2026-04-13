package com.example.core.entities.shared.physicals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.example.shared.exceptions.BuildException;

public class PhysicalDataTest {

    @Test
    void testGetInstanceValid() throws BuildException {
        PhysicalData pd = PhysicalData.getInstance(1.0, 2.0, 3.0, 4.0);
        assertEquals(1.0, pd.getWeight());
        assertEquals(2.0, pd.getHeight());
        assertEquals(3.0, pd.getWidth());
        assertEquals(4.0, pd.getDepth());
    }

    @Test
    void testGetInstanceInvalidWeight() {
        assertThrows(BuildException.class, () -> PhysicalData.getInstance(0, 2.0, 3.0, 4.0));
    }

    @Test
    void testGetInstanceInvalidHeight() {
        assertThrows(BuildException.class, () -> PhysicalData.getInstance(1.0, 0, 3.0, 4.0));
    }

    @Test
    void testGetInstanceInvalidWidth() {
        assertThrows(BuildException.class, () -> PhysicalData.getInstance(1.0, 2.0, 0, 4.0));
    }

    @Test
    void testGetInstanceInvalidDepth() {
        assertThrows(BuildException.class, () -> PhysicalData.getInstance(1.0, 2.0, 3.0, 0));
    }

    @Test
    void testSetWeight() throws BuildException {
        PhysicalData pd = PhysicalData.getInstance(1.0, 2.0, 3.0, 4.0);
        assertEquals(0, pd.setWeight(5.0));
        assertEquals(5.0, pd.getWeight());
        assertEquals(-1, pd.setWeight(0));
    }

    @Test
    void testSetHeight() throws BuildException {
        PhysicalData pd = PhysicalData.getInstance(1.0, 2.0, 3.0, 4.0);
        assertEquals(0, pd.setHeight(5.0));
        assertEquals(5.0, pd.getHeight());
        assertEquals(-1, pd.setHeight(0));
    }

    @Test
    void testSetWidth() throws BuildException {
        PhysicalData pd = PhysicalData.getInstance(1.0, 2.0, 3.0, 4.0);
        assertEquals(0, pd.setWidth(5.0));
        assertEquals(5.0, pd.getWidth());
        assertEquals(-1, pd.setWidth(0));
    }

    @Test
    void testSetDepth() throws BuildException {
        PhysicalData pd = PhysicalData.getInstance(1.0, 2.0, 3.0, 4.0);
        assertEquals(0, pd.setDepth(5.0));
        assertEquals(5.0, pd.getDepth());
        assertEquals(-1, pd.setDepth(0));
    }

    @Test
    void testGetVolume() throws BuildException {
        PhysicalData pd = PhysicalData.getInstance(1.0, 2.0, 3.0, 4.0);
        assertEquals(24.0, pd.getVolume());
    }

    @Test
    void testGetArea() throws BuildException {
        PhysicalData pd = PhysicalData.getInstance(1.0, 2.0, 3.0, 4.0);
        assertEquals(6.0, pd.getArea());
    }

    @Test
    void testGetSize() throws BuildException {
        PhysicalData pd = PhysicalData.getInstance(1.0, 2.0, 3.0, 4.0);
        assertEquals("height: 2.0; width: 3.0; depth: 4.0", pd.getSize());
    }
}
