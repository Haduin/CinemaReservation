package com.cinemareservation.screening;

import com.cinemareservation.move.MovieRest;
import com.cinemareservation.room.Room;
import com.cinemareservation.seat.Seat;
import com.cinemareservation.seatReservatiion.SeatReservation;
import com.cinemareservation.exceptions.ScreeningNotFoundException;
import com.cinemareservation.reservation.Reservation;
import com.cinemareservation.seat.SeatRest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScreeningServiceImpl implements ScreeningService {

    private final ScreeningRepository screeningRepository;

    @Override
    public ScreeningRest getDetails(Long screeningId) {

        return screeningRepository.findById(screeningId).map(screening -> {
            Room room = screening.getRoom();
            List<SeatReservation> seatReservations =
                    screening.getReservations()
                            .stream()
                            .map(Reservation::getSeatReservations)
                            .flatMap(Collection::stream)
                            .toList();

            return ScreeningRest.builder()
                    .id(screening.getId())
                    .movieRest(MovieRest.builder().id(screening.getMovie().getId()).title(screening.getMovie().getTitle()).build())
                    .startTime(screening.getStartTime())
                    .seats(room.getSeats().stream().map(seat -> SeatRest.builder().id(seat.getId()).row(seat.getRow()).col(seat.getCol()).taken(checkIfSeatIsTaken(seatReservations, seat)).build()).collect(Collectors.toList()))
                    .build();
        }).orElseThrow(ScreeningNotFoundException::new);
    }

    public static Boolean checkIfSeatIsTaken(List<SeatReservation> reservations, Seat seat) {
        if(reservations.isEmpty()){
            return Boolean.FALSE;
        }
        return reservations.stream()
                .filter(seatReservation -> Objects.equals(seatReservation.getSeat().getId(), seat.getId()))
                .findFirst()
                .map(seatReservation -> Boolean.TRUE)
                .orElse(Boolean.FALSE);

    }
}
