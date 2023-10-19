package com.cinemareservation.reservation;

public interface ReservationService {
    ReservationResponse makeReservation(final ReservationRequest request);
}
