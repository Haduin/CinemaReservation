package com.nussknacker.cinemareservation.reservation;

import lombok.Getter;

@Getter
public enum TICKET_TYPE {
    ADULT(25),
    STUDENT(18),
    CHILD(12.50);

    private final Double price;

    TICKET_TYPE(double price) {
        this.price = price;
    }
}
