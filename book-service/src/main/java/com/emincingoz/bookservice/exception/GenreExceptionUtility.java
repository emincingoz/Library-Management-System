package com.emincingoz.bookservice.exception;

import com.emincingoz.bookservice.exception.core.model.ExceptionData;

import org.springframework.http.HttpStatus;

public final class GenreExceptionUtility extends ApplicationExceptionUtility {

    private static final String APP_NAME = "GENRE_SERVICE";
    public static final ExceptionData GENRE_NOT_FOUND = new ExceptionData(APP_NAME, HttpStatus.NOT_FOUND.value(), "Genre Not Found");
    public static final ExceptionData GENRE_ALREADY_ADDED = new ExceptionData(APP_NAME, HttpStatus.CONFLICT.value(), "There is a Genre Already Added");
    private GenreExceptionUtility() {
        super();
    }

}
