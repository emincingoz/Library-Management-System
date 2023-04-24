package com.emincingoz.bookservice.exception;

public final class BookException extends RuntimeException {

    public BookException(String message) {
        super(message);
    }

    public static final String INVALID_PARAMETER = "Invalid Parameter.";

    public static final String BOOK_NOT_FOUND = "Book Not Found.";

    public static final String BOOK_ALREADY_ADDED = "There is a Book Already Added.";

    public static BookException invalidParameter(String exception) {
        return new BookException(exception);
    }

    public static BookException dataNotFoundException(String exception) {
        return new BookException(exception);
    }
}
