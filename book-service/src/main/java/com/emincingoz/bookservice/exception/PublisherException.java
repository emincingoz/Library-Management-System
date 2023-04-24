package com.emincingoz.bookservice.exception;

public class PublisherException extends RuntimeException {

    public PublisherException(String message) {
        super(message);
    }

    public static final String INVALID_PARAMETER = "Invalid Parameter.";

    public static final String PUBLISHER_NOT_FOUND = "Publisher Not Found.";

    public static final String PUBLISHER_ALREADY_ADDED = "There is a Publisher Already Added.";

    public static PublisherException invalidParameter(String exception) {
        return new PublisherException(exception);
    }

    public static PublisherException dataNotFoundException(String exception) {
        return new PublisherException(exception);
    }
}
