package com.cinemareservation.reservation;

import java.time.LocalDateTime;


public record ReservationResponse(String nameOfBooker, Double payment, LocalDateTime expirationDate) {
}
