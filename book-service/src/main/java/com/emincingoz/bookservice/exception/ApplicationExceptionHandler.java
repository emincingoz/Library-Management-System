package com.emincingoz.bookservice.exception;

import java.util.HashMap;
import java.util.Map;

import com.emincingoz.bookservice.exception.core.BusinessException;
import com.emincingoz.bookservice.exception.core.DataNotFoundException;
import com.emincingoz.bookservice.exception.core.InvalidRequestException;
import com.emincingoz.bookservice.exception.core.model.ExceptionData;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Controller Advice Class
 * General Exception Handler
 *
 * @author Emin Cingoz
 * @version 5/1/2023
 */
@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    private final String APP_NAME = "book-service";

    /**
     * Generates error response map
     *
     * @param throwable
     * @param exceptionData
     * @return Map<String, String>
     */
    private static Map<String, String> generateErrorResponseMap(Throwable throwable, ExceptionData exceptionData) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", throwable.getMessage());
        errorMap.put("errorCode", exceptionData.getErrorCode().toString());
        errorMap.put("applicationName", exceptionData.getApplicationName());
        StackTraceElement[] stackTraceElements = throwable.getStackTrace();
        errorMap.put("line", String.valueOf(stackTraceElements[1].getLineNumber()));
        errorMap.put("classPath", stackTraceElements[1].getClassName());
        errorMap.put("method", stackTraceElements[1].getMethodName());
        return errorMap;
    }

    /**
     * WhiteSpaceChecker validation throws an error, handleValidationException method catches it
     *
     * @param exception
     * @return ResponseEntity<Map < String, String>>
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(ConstraintViolationException exception) {
        ExceptionData exceptionData = ApplicationExceptionUtility.WHITE_SPACE_VALIDATION_EXCEPTION;
        Map<String, String> errorResponseMap = generateErrorResponseMap(exception, exceptionData);
        return new ResponseEntity<>(errorResponseMap, HttpStatus.OK);
    }

    /**
     * Since the codes with 400 or 500 fell into the catch block in feign or restTemplate call, 200 was returned as a response. Therefore, error code shared in json.
     * When InvalidRequestException is thrown, an error is caught with RestControllerAdvice and a clear error message is returned to the user.
     *
     * @param invalidRequestException
     * @return ResponseEntity<Map < String, String>>
     */
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Map<String, String>> handleInvalidRequest(InvalidRequestException invalidRequestException) {
        Map<String, String> errorMap = generateErrorResponseMap(invalidRequestException, invalidRequestException.getExceptionData());
        return new ResponseEntity<>(errorMap, HttpStatus.OK);
    }

    /**
     * When DataNotFoundException is thrown, an error is caught with RestControllerAdvice and a clear error message is returned to the user.
     *
     * @param dataNotFoundException
     * @return ResponseEntity<Map < String, String>>
     */
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleDataNotFound(DataNotFoundException dataNotFoundException) {
        Map<String, String> errorMap = generateErrorResponseMap(dataNotFoundException, dataNotFoundException.getExceptionData());
        return new ResponseEntity<>(errorMap, HttpStatus.OK);
    }

    // TODO:: stackTraceElements[1] check null

    /**
     * When BusinessException is thrown, an error is caught with RestControllerAdvice and a clear error message is returned to the user.
     *
     * @param businessException
     * @return ResponseEntity<Map < String, String>>
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, String>> handleBusinessException(BusinessException businessException) {
        Map<String, String> errorMap = generateErrorResponseMap(businessException, businessException.getExceptionData());
        return new ResponseEntity<>(errorMap, HttpStatus.OK);
    }

}
