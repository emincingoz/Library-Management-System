package com.emincingoz.bookservice.exception;

public class InterpreterException extends RuntimeException {

    public InterpreterException(String message) {
        super(message);
    }

    public static final String INVALID_PARAMETER = "Invalid Parameter.";

    public static final String INTERPRETER_NOT_FOUND = "Interpreter Not Found.";

    public static final String INTERPRETER_ALREADY_ADDED = "There is a Interpreter Already Added.";

    public static InterpreterException invalidParameter(String exception) {
        return new InterpreterException(exception);
    }

    public static InterpreterException dataNotFoundException(String exception) {
        return new InterpreterException(exception);
    }
}
