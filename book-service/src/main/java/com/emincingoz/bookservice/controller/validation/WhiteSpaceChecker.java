package com.emincingoz.bookservice.controller.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = WhiteSpaceValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface WhiteSpaceChecker {

    String message() default "Invalid parameter, no spaces between words. Words must be combined with '+'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
