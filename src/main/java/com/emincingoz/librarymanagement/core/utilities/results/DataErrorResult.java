package com.emincingoz.librarymanagement.core.utilities.results;

public class DataErrorResult<T> extends DataResult{

    public DataErrorResult(T data) {
        super(data, false);
    }

    public DataErrorResult(T data, String message) {
        super(data, message, false);
    }
}
