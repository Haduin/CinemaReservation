package com.cinemareservation.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StartWithCapitalWithDoubleSurnameValidator implements ConstraintValidator<StartWithCapitalWithDoubleSurname, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()){
            return false;
        }

        int hyphenIndex = value.indexOf('-');
        if (hyphenIndex == -1 || hyphenIndex == value.length() - 1) {
            return false;
        }

        char nextChar = value.charAt(hyphenIndex + 1);
        return Character.isUpperCase(nextChar) && Character.isUpperCase(value.charAt(0));
    }

}
