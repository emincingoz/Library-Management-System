package com.emincingoz.bookservice.exception.core.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionData {
    private String applicationName;
    private Integer errorCode;
    private String errorMessage;

    public ExceptionData(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public ExceptionData(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ExceptionData(String applicationName, Integer errorCode, String errorMessage) {
        this.applicationName = applicationName;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
