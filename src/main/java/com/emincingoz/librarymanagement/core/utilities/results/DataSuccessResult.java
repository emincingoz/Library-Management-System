package com.emincingoz.librarymanagement.core.utilities.results;

public class DataSuccessResult<T> extends DataResult{

    public DataSuccessResult(T data) {
        super(data, false);
    }

    public DataSuccessResult(T data, String message) {
        super(data, message, true);
    }
}
