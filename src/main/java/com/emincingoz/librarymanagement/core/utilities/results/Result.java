package com.emincingoz.librarymanagement.core.utilities.results;

import lombok.Getter;

@Getter
public class Result {
    private String message;
    private boolean success;

    public Result(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public Result(boolean success) {
        this.success = success;
    }
}
