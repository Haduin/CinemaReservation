package com.cinemareservation.screening;

import com.cinemareservation.seat.Seat;
import com.cinemareservation.seatReservatiion.SeatReservation;
import com.cinemareservation.exceptions.ScreeningNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScreeningServiceImplTest {

    @InjectMocks
    private ScreeningServiceImpl screeningService;

    @Mock
    private ScreeningRepository screeningRepository;


    @Test
    public void getScreeningRestShouldThrowScreeningNotFoundException() {
        Long screeningId = 1L;

        when(screeningRepository.findById(screeningId)).thenReturn(Optional.empty());

        assertThrows(ScreeningNotFoundException.class, () -> screeningService.getDetails(screeningId));
    }

    @Test
    public void shouldFailWhenSeatIsReserved() {
        Seat seat = new Seat();
        seat.setId(1L);
        List<SeatReservation> reservations = new ArrayList<>();
        SeatReservation reservation = new SeatReservation();
        reservation.setSeat(seat);
        reservations.add(reservation);

        assertTrue(ScreeningServiceImpl.checkIfSeatIsTaken(reservations, seat));

    }

    @Test
    public void shouldPassWhenSeatIsReserved() {
        Seat seat = new Seat();
        seat.setId(1L);
        List<SeatReservation> reservations = new ArrayList<>();
        SeatReservation reservation = new SeatReservation();
        reservation.setSeat(new Seat());
        reservations.add(reservation);

        assertFalse(ScreeningServiceImpl.checkIfSeatIsTaken(reservations, seat));
    }
}