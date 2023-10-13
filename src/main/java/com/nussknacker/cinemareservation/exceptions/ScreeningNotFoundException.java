package com.nussknacker.cinemareservation.exceptions;

public class ScreeningNotFoundException extends RuntimeException
{
    public ScreeningNotFoundException(String message) {
        super(message);
    }
}
