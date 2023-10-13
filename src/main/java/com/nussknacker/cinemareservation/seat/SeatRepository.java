package com.nussknacker.cinemareservation.seat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

//    @Query(value = "Select Seat from Seat s,SeatReservation sr where s.id not in sr.seat.id")
//    List<Seat> findAllFreeSeats();

}
