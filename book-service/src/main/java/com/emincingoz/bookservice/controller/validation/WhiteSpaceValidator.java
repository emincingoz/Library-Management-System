package com.emincingoz.bookservice.controller.validation;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

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
        if (StringUtils.isNumeric(s) || StringUtils.isNumeric(s.replace("+", ""))) {
            return false;
        }

        // If the + signs are side by side in the parameter, validation should return false.
        AtomicReference<Boolean> flagEmptyString = new AtomicReference<>(false);
        String[] parts = s.split("\\+");
        Arrays.stream(parts).forEach(part -> {
            if (part.isEmpty()) {
                flagEmptyString.set(true);
            }
        });
        return !Boolean.TRUE.equals(flagEmptyString.get());
    }
}
