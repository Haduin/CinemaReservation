package com.cinemareservation.exceptions;

import java.util.List;
import java.util.stream.Collectors;

public class SeatsTakenException extends  RuntimeException {
    public SeatsTakenException(List<Long> seatsId) {
        super(seatsId.stream().map(String::valueOf).collect(Collectors.joining(",")));
    }
}
