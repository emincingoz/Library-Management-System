package com.emincingoz.bookservice.util;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Book Service Utility Class
 *
 * @author Emin Cingoz
 * @version 5/2/2023
 */
public final class BookServiceUtil {
    /**
     * Private constructor better to use in classes with has static methods
     */
    private BookServiceUtil() {
    }

    /**
     * decodeInput removes the + signs in the parameter from the controller and returns it to the string format used in the project.
     *
     * @param input
     * @return String
     */
    public static String decodeInput(String input) {
        String newValue = input.replace("+", " ");
        return Arrays.stream(newValue.split("\\s+")).map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase()).collect(Collectors.joining(" "));
    }
}
