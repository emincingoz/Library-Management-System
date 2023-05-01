package com.emincingoz.bookservice.exception;

import com.emincingoz.bookservice.exception.core.model.ExceptionData;

import org.springframework.http.HttpStatus;

public final class BookExceptionUtility extends ApplicationExceptionUtility {

    private static final String APP_NAME = "BOOK_SERVICE";
    public static final ExceptionData BOOK_NOT_FOUND = new ExceptionData(APP_NAME, HttpStatus.NOT_FOUND.value(), "Book Not Found");
    public static final ExceptionData BOOK_ALREADY_ADDED = new ExceptionData(APP_NAME, HttpStatus.CONFLICT.value(), "Book Already Added");

    /**
     * A better approach is that classes with static fields do not have a public access specifier.
     * <p>
     * Sonar issue: Utility classes, which are collections of static members, are not meant to be instantiated.
     * Even abstract utility classes, which can be extended, should not have public constructors.
     * Java adds an implicit public constructor to every class which does not define at least one explicitly.
     * Hence, at least one non-public constructor should be defined.
     */
    private BookExceptionUtility() {
        super();
    }

}
