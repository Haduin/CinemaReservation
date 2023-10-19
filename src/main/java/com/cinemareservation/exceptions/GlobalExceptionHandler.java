package com.cinemareservation.exceptions;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Configuration
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDetails handleInvalidArgumentException(InvalidArgumentException ex) {
        return new ErrorDetails(ex.getMessage());
    }

    @ExceptionHandler(ScreeningNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDetails handleScreeningNotFoundException(ScreeningNotFoundException ex) {
        return new ErrorDetails(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = new ArrayList<>();

        ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));

        return handleExceptionInternal(ex, new ErrorDetails(null, errors), headers, HttpStatusCode.valueOf(400), request);
    }

    @ExceptionHandler(SeatsTakenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDetails handleSeatsTakenException(SeatsTakenException ex) {
        return new ErrorDetails(String.format("You can't choose reserved seat: %s", ex.getMessage()));
    }
    @ExceptionHandler(ScreeningReservationNotPossibleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDetails handleScreeningReservationNotPossibleException(ScreeningReservationNotPossibleException ex) {
        return new ErrorDetails(ex.getMessage());
    }

}
