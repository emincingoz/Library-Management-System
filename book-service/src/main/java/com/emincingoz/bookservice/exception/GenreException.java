package com.emincingoz.bookservice.exception;

public class GenreException extends RuntimeException {

    public GenreException(String message) {
        super(message);
    }

    public static final String INVALID_PARAMETER = "Invalid Parameter.";

    public static final String GENRE_NOT_FOUND = "Genre Not Found.";

    public static final String GENRE_ALREADY_ADDED = "There is a Genre Already Added.";

    public static GenreException invalidParameter(String exception) {
        return new GenreException(exception);
    }

    public static GenreException dataNotFoundException(String exception) {
        return new GenreException(exception);
    }
}
