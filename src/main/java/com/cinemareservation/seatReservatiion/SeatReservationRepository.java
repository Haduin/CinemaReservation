package com.cinemareservation.seatReservatiion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatReservationRepository extends JpaRepository<SeatReservation, Long> {
}
