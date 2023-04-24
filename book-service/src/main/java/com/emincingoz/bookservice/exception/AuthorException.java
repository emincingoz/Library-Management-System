package com.emincingoz.bookservice.exception;

public class AuthorException  extends RuntimeException {

    public AuthorException(String message) {
        super(message);
    }

    public static final String INVALID_PARAMETER = "Invalid Parameter.";

    public static final String AUTHOR_NOT_FOUND = "Author Not Found.";

    public static final String AUTHOR_ALREADY_ADDED = "There is a Author Already Added.";

    public static AuthorException invalidParameter(String exception) {
        return new AuthorException(exception);
    }

    public static AuthorException dataNotFoundException(String exception) {
        return new AuthorException(exception);
    }
}
