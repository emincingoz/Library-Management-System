package com.emincingoz.bookservice.controller.validation;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.google.common.base.Strings;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class WhiteSpaceValidator implements ConstraintValidator<WhiteSpaceChecker, String> {
    @Override
    public void initialize(WhiteSpaceChecker constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (Strings.isNullOrEmpty(s)) {
            return false;
        }
        if (s.contains(" ")) {
            return false;
        }
        if (s.replace("+", "").length() == 0) {
            return false;
        }
//        String trimmedString = s.trim().replaceAll("\\s+", "+");
        String newValue = s.replace("+", " ");
        s = Arrays.stream(newValue.split("\\s+"))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
        return true;
    }
}
