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
@Constraint(validatedBy = StartsWithCapitalValidator.class)
public @interface StartWithCapital {
    String message() default "name and surname should start with capital letter";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


