package com.emincingoz.bookservice.exception;

import com.emincingoz.bookservice.exception.core.BusinessException;
import com.emincingoz.bookservice.exception.core.DataNotFoundException;
import com.emincingoz.bookservice.exception.core.InvalidRequestException;
import com.emincingoz.bookservice.exception.core.model.ExceptionData;

import org.springframework.http.HttpStatus;

public abstract class ApplicationExceptionUtility {

    private static final String APP_NAME = "BOOK_SERVICE_MODULE";


    public static final ExceptionData INVALID_PARAMETER = new ExceptionData(APP_NAME, HttpStatus.UNPROCESSABLE_ENTITY.value(), "Invalid Parameter Used. Parameter Null or Empty");
    public static final ExceptionData WHITE_SPACE_VALIDATION_EXCEPTION = new ExceptionData(APP_NAME, HttpStatus.UNPROCESSABLE_ENTITY.value(), "Invalid parameter, no spaces between words. Words must be combined with '+'");

    ApplicationExceptionUtility() {
    }

    public static InvalidRequestException invalidRequestException(ExceptionData exceptionData) {
        return new InvalidRequestException(exceptionData);
    }

    public static DataNotFoundException dataNotFoundException(ExceptionData exceptionData) {
        return new DataNotFoundException(exceptionData);
    }

    public static BusinessException businessException(ExceptionData exceptionData) {
        return new BusinessException(exceptionData);
    }
}
