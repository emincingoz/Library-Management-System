package com.emincingoz.bookservice.exception;

import com.emincingoz.bookservice.exception.core.model.ExceptionData;

import org.springframework.http.HttpStatus;

public final class InterpreterExceptionUtility extends ApplicationExceptionUtility {

    private static final String APP_NAME = "INTERPRETER_SERVICE";
    public static final ExceptionData INTERPRETER_NOT_FOUND = new ExceptionData(APP_NAME, HttpStatus.NOT_FOUND.value(), "Interpreter Not Found");
    public static final ExceptionData INTERPRETER_ALREADY_ADDED = new ExceptionData(APP_NAME, HttpStatus.CONFLICT.value(), "There is a Interpreter Already Added");

    private InterpreterExceptionUtility() {
        super();
    }

}
