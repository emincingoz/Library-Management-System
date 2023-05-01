package com.emincingoz.bookservice.exception.core;

import com.emincingoz.bookservice.exception.core.model.ExceptionData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidRequestException extends RuntimeException {

    private ExceptionData exceptionData;

    public InvalidRequestException(ExceptionData exceptionData) {
        super(exceptionData.getErrorMessage());
        this.exceptionData = exceptionData;
    }
}
