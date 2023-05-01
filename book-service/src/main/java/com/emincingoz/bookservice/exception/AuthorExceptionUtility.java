package com.emincingoz.bookservice.exception;

import com.emincingoz.bookservice.exception.core.model.ExceptionData;

import org.springframework.http.HttpStatus;

public final class AuthorExceptionUtility extends ApplicationExceptionUtility {

    private static final String APP_NAME = "AUTHOR_SERVICE";
    public static final ExceptionData AUTHOR_NOT_FOUND = new ExceptionData(APP_NAME, HttpStatus.NOT_FOUND.value(), "Author Not Found");
    public static final ExceptionData AUTHOR_ALREADY_ADDED = new ExceptionData(APP_NAME, HttpStatus.CONFLICT.value(), "There is a Author Already Added");

    private AuthorExceptionUtility() {
        super();
    }

}
