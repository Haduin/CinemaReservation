package com.nussknacker.cinemareservation.screening;

import com.nussknacker.cinemareservation.exceptions.ScreeningNotFoundException;
import com.nussknacker.cinemareservation.move.MovieRest;
import com.nussknacker.cinemareservation.reservation.Reservation;
import com.nussknacker.cinemareservation.room.Room;
import com.nussknacker.cinemareservation.seat.Seat;
import com.nussknacker.cinemareservation.seat.SeatRest;
import com.nussknacker.cinemareservation.seatReservatiion.SeatReservation;
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

            List<Seat> seats = room.getSeats();

            return ScreeningRest.builder()
                    .id(screening.getId())
                    .movieRest(MovieRest.builder().id(screening.getMovie().getId()).title(screening.getMovie().getTitle()).build())
                    .startTime(screening.getStartTime())
                    .seats(seats.stream().map(seat -> SeatRest.builder().id(seat.getId()).row(seat.getRow()).col(seat.getCol()).taken(checkIfSeatIsTaken(seatReservations, seat)).build()).collect(Collectors.toList())).build();
        }).orElseThrow(() -> new ScreeningNotFoundException("Screening with passed id has not been found"));
    }

    private Boolean checkIfSeatIsTaken(List<SeatReservation> reservations, Seat seat) {
        return reservations.stream()
                .filter(seatReservation -> Objects.equals(seatReservation.getSeat().getId(), seat.getId()))
                .findFirst()
                .map(seatReservation -> Boolean.TRUE)
                .orElse(Boolean.FALSE);

    }
}
