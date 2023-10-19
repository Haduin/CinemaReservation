package com.cinemareservation.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class ErrorDetails {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");

    private final String timestamp;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errors;

    public ErrorDetails(String message, List<String> errors) {
        this.timestamp = LocalDateTime.now().format(dateTimeFormatter);
        this.message = message;
        this.errors = errors;
    }

    public ErrorDetails(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now().format(dateTimeFormatter);
    }
}
