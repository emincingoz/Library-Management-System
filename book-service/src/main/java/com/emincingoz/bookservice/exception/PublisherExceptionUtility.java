package com.emincingoz.bookservice.exception;

import com.emincingoz.bookservice.exception.core.model.ExceptionData;

import org.springframework.http.HttpStatus;

public final class PublisherExceptionUtility extends ApplicationExceptionUtility {

    private static final String APP_NAME = "PUBLISHER_SERVICE";
    public static final ExceptionData PUBLISHER_NOT_FOUND = new ExceptionData(APP_NAME, HttpStatus.NOT_FOUND.value(), "Publisher Not Found");
    public static final ExceptionData PUBLISHER_ALREADY_ADDED = new ExceptionData(APP_NAME, HttpStatus.CONFLICT.value(), "There is a Publisher Already Added");
    private PublisherExceptionUtility() {
        super();
    }


}
