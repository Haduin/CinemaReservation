package com.cinemareservation.exceptions;

public class ScreeningNotFoundException extends RuntimeException
{
    public ScreeningNotFoundException() {
        super("Screening with passed id has not been found");
    }
}
