package com.example.core.entities.shared.validations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.shared.exceptions.GeneralDateTimeException;

public class Check {

    public static boolean isNullString(String s) {
        return s == null;
    }

    public static boolean minStringChars(String s, int minChars) {
        return (s != null) && (s.trim().length() >= minChars);
    }

    public static boolean maxStringChars(String s, int maxChars) {
        return (s != null) && (s.trim().length() <= maxChars);
    }

    public static boolean isValidString(String s, int minChars, int maxChars) {
        if (maxChars == 0) {
            return minStringChars(s, minChars);
        } else {
            return minStringChars(s, minChars) && maxStringChars(s, maxChars);
        }
    }

    //valid numbers =====================
    public static boolean isValidNumber(int value, int minValue) {
        if (value < minValue) {
            return false;
        }
        return true;
    }

    public static boolean isValidNumber(double value, double minValue) {
        if (value < minValue) {
            return false;
        }
        return true;
    }

    //======================================
    public static boolean ISBN(String isbn) {
        if (isbn == null) {
            return false;
        }
        isbn = isbn.replaceAll("-", "").replaceAll(" ", "");
        int length = isbn.length();

        if (length == 10) {
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                if (!Character.isDigit(isbn.charAt(i))) {
                    return false;
                }
                sum += (isbn.charAt(i) - '0') * (10 - i);
            }
            char lastChar = isbn.charAt(9);
            if (lastChar != 'X' && !Character.isDigit(lastChar)) {
                return false;
            }
            sum += (lastChar == 'X') ? 10 : (lastChar - '0');
            return sum % 11 == 0;
        } else if (length == 13) {
            int sum = 0;
            for (int i = 0; i < 13; i++) {
                if (!Character.isDigit(isbn.charAt(i))) {
                    return false;
                }
                int digit = isbn.charAt(i) - '0';
                sum += (i % 2 == 0) ? digit : digit * 3;
            }
            return sum % 10 == 0;
        }
        return false;
    }

    public static LocalDateTime convertStringToDateTime(String dateTimeString, DateTimeFormatter formatter) throws GeneralDateTimeException {
        if (dateTimeString == null || formatter == null) {
            throw new GeneralDateTimeException("Invalid input");
        }
        try {
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (java.time.format.DateTimeParseException e) {
            throw new GeneralDateTimeException("Error parsing date-time: " + e.getMessage());
        }
    }

    public static String convertDateTimeToString(LocalDateTime dateTime, DateTimeFormatter formatter) throws GeneralDateTimeException {
        if (dateTime == null || formatter == null) {

            throw new GeneralDateTimeException("Invalid input");
        }
        return dateTime.format(formatter);
    }


    //ejemplo de sobrecarga de metodos
    // public static boolean minValue(int value, int minValue) {
    //     if (value < minValue) {
    //         return false;
    //     }
    //     return true;
    // }
    // public static boolean minValue(String s, int minChars) {
    //     return (s != null) && (s.trim().length() >= minChars);
    // }
}
