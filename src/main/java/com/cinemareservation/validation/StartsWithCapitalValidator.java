package com.cinemareservation.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StartsWithCapitalValidator implements ConstraintValidator<StartWithCapital, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        return Character.isUpperCase(value.charAt(0));
    }
}