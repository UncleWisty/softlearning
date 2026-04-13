package com.example.core.entities.shared.validations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.shared.exceptions.GeneralDateTimeException;

public class CheckTest {

    @Test
    void testIsNullString() {
        assertTrue(Check.isNullString(null));
        assertFalse(Check.isNullString(""));
        assertFalse(Check.isNullString("test"));
    }

    @Test
    void testMinStringChars() {
        assertFalse(Check.minStringChars(null, 1));
        assertFalse(Check.minStringChars("", 1));
        assertFalse(Check.minStringChars("a", 2));
        assertTrue(Check.minStringChars("ab", 2));
        assertTrue(Check.minStringChars("  a  ", 1));
    }

    @Test
    void testMaxStringChars() {
        assertFalse(Check.maxStringChars(null, 1));
        assertTrue(Check.maxStringChars("", 1));
        assertTrue(Check.maxStringChars("a", 1));
        assertFalse(Check.maxStringChars("ab", 1));
        assertTrue(Check.maxStringChars("  a  ", 5));
    }

    @Test
    void testIsValidString() {
        assertFalse(Check.isValidString(null, 1, 5));
        assertFalse(Check.isValidString("", 1, 5));
        assertFalse(Check.isValidString("a", 2, 5));
        assertTrue(Check.isValidString("abc", 2, 5));
        assertFalse(Check.isValidString("abcdef", 2, 5));
        assertTrue(Check.isValidString("abc", 2, 0));
    }

    @Test
    void testIsValidNumberInt() {
        assertFalse(Check.isValidNumber(-1, 0));
        assertTrue(Check.isValidNumber(0, 0));
        assertTrue(Check.isValidNumber(5, 0));
        assertFalse(Check.isValidNumber(5, 10));
    }

    @Test
    void testIsValidNumberDouble() {
        assertFalse(Check.isValidNumber(-1.0, 0.0));
        assertTrue(Check.isValidNumber(0.0, 0.0));
        assertTrue(Check.isValidNumber(5.5, 0.0));
        assertFalse(Check.isValidNumber(5.5, 10.0));
    }

    @Test
    void testISBN() {
        assertFalse(Check.ISBN(null));
        assertFalse(Check.ISBN(""));
        assertFalse(Check.ISBN("123"));
        assertTrue(Check.ISBN("0306406152"));
        assertTrue(Check.ISBN("978-0-306-40615-7"));
        assertFalse(Check.ISBN("0306406153"));
    }

    @Test
    void testConvertStringToDateTime() throws GeneralDateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime expected = LocalDateTime.of(2023, 1, 1, 12, 0, 0);
        LocalDateTime result = Check.convertStringToDateTime("2023-01-01 12:00:00", formatter);
        assertEquals(expected, result);
    }

    @Test
    void testConvertStringToDateTimeInvalid() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        assertThrows(GeneralDateTimeException.class, () -> Check.convertStringToDateTime(null, formatter));
        assertThrows(GeneralDateTimeException.class, () -> Check.convertStringToDateTime("2023-01-01 12:00:00", null));
        assertThrows(GeneralDateTimeException.class, () -> Check.convertStringToDateTime("invalid", formatter));
    }

    @Test
    void testConvertDateTimeToString() throws GeneralDateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.of(2023, 1, 1, 12, 0, 0);
        String result = Check.convertDateTimeToString(dateTime, formatter);
        assertEquals("2023-01-01 12:00:00", result);
    }

    @Test
    void testConvertDateTimeToStringInvalid() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        assertThrows(GeneralDateTimeException.class, () -> Check.convertDateTimeToString(null, formatter));
        assertThrows(GeneralDateTimeException.class, () -> Check.convertDateTimeToString(LocalDateTime.now(), null));
    }
}
