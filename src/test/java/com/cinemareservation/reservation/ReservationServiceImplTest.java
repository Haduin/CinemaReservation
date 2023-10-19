package com.cinemareservation.reservation;

import com.cinemareservation.screening.Screening;
import com.cinemareservation.seat.Seat;
import com.cinemareservation.seat.SeatType;
import com.cinemareservation.seatReservatiion.SeatReservationRepository;
import com.cinemareservation.screening.ScreeningRepository;
import com.cinemareservation.seat.SeatRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {
    @InjectMocks
    private ReservationServiceImpl reservationService;
    @Mock
    ReservationRepository reservationRepository;
    @Mock
    SeatReservationRepository seatReservationRepository;
    @Mock
    ScreeningRepository screeningRepository;
    @Mock
    SeatRepository seatRepository;

    @Test
    public void makeReservation_Successful() {
        ReservationRequest request = new ReservationRequest();
        request.setName("Test");
        request.setScreeningId(1L);
        request.setSeatsWithTypes(List.of(new SeatType(1L,TICKET_TYPE.ADULT),new SeatType(2L,TICKET_TYPE.ADULT)));

        Screening screening = new Screening();
        screening.setStartTime(LocalDateTime.now().plusHours(1));
        List<Reservation> reservations = new ArrayList<>();

        screening.setReservations(reservations);

        when(screeningRepository.findById(anyLong())).thenReturn(Optional.of(screening));

        List<Seat> seats = List.of(Seat.builder().id(1L).row(11L).col(11L).build(), Seat.builder().id(2L).row(12L).col(11L).build());
        when(seatRepository.findAllById(List.of(1L,2L))).thenReturn(seats);

        assertEquals("Test",reservationService.makeReservation(request).nameOfBooker());
        assertEquals(50.0,reservationService.makeReservation(request).payment());
    }



}