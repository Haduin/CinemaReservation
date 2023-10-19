package com.cinemareservation.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Documented
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = StartWithCapitalWithDoubleSurnameValidator.class)
public @interface StartWithCapitalWithDoubleSurname {
    String message() default "both surnames should start with capital letters between -";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
