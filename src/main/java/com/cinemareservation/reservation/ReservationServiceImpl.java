package com.cinemareservation.reservation;

import com.cinemareservation.exceptions.ScreeningNotFoundException;
import com.cinemareservation.exceptions.ScreeningReservationNotPossibleException;
import com.cinemareservation.exceptions.SeatsTakenException;
import com.cinemareservation.screening.Screening;
import com.cinemareservation.screening.ScreeningRepository;
import com.cinemareservation.screening.ScreeningServiceImpl;
import com.cinemareservation.seat.Seat;
import com.cinemareservation.seat.SeatType;
import com.cinemareservation.seatReservatiion.SeatReservation;
import com.cinemareservation.seatReservatiion.SeatReservationRepository;
import com.cinemareservation.seat.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final SeatReservationRepository seatReservationRepository;
    private final ScreeningRepository screeningRepository;
    private final SeatRepository seatRepository;

    @Override
    public ReservationResponse makeReservation(final ReservationRequest request) {
        Screening screening = screeningRepository.findById(request.getScreeningId())
                .orElseThrow(ScreeningNotFoundException::new);

        List<Seat> requestSeats = seatRepository.findAllById(request.getSeatsWithTypes().stream().map(SeatType::id).toList());

        Map<Boolean, List<Seat>> takenNotTakenSeats = requestSeats.stream()
                .collect(Collectors.partitioningBy(seat -> ScreeningServiceImpl.checkIfSeatIsTaken(screening.getReservations()
                        .stream()
                        .map(Reservation::getSeatReservations)
                        .flatMap(Collection::stream)
                        .toList(), seat)));


        List<Seat> seats = takenNotTakenSeats.get(Boolean.FALSE).stream().toList();

        if (!takenNotTakenSeats.get(Boolean.TRUE).isEmpty()) {
            throw new SeatsTakenException(takenNotTakenSeats.get(Boolean.TRUE).stream().map(Seat::getId).toList());
        } else if (!canReserveSeats(seats)) {
            throw new ScreeningReservationNotPossibleException("There cannot be place left between");
        }

        return makeReservation(screening, seats, request, LocalDateTime.now());

    }

    private boolean canReserveSeats(List<Seat> seatDTOs) {
        if (seatDTOs.isEmpty()) {
            return false;
        }


        Seat firstSeat = seatDTOs.get(0);
        Long previousRow = firstSeat.getRow();

        for (int i = 1; i < seatDTOs.size(); i++) {
            Seat currentSeat = seatDTOs.get(i);
            Long currentRow = currentSeat.getRow();

            if (Math.abs(currentRow - previousRow) > 1) {
                return false;
            }

            previousRow = currentRow;
        }

        return true;
    }

    private ReservationResponse makeReservation(Screening screening, List<Seat> seats, ReservationRequest request, LocalDateTime now) {
        if (Duration.between(now,screening.getStartTime()).getSeconds() / 60 > 15) {

            Reservation reservation = Reservation.builder().activeTill(screening.getStartTime()).screening(screening).surname(request.getSurname()).name(request.getName()).build();
            reservationRepository.save(reservation);

            List<SeatReservation> seatsReservations = seats.stream()
                    .map(seat -> SeatReservation.builder().reservation(reservation).seat(seat).screening(screening).type(findType(seat.getId(), request)).build())
                    .toList();

            double reservationPayment = seatsReservations.stream().map(seatReservation -> seatReservation.getType().getPrice()).mapToDouble(value -> value).sum();
            seatReservationRepository.saveAll(seatsReservations);
            return new ReservationResponse(request.getName(), reservationPayment, reservation.getActiveTill());
        } else {
            throw new ScreeningReservationNotPossibleException("You can make reservation only 15min before screening starts ");
        }
    }

    private TICKET_TYPE findType(Long id, ReservationRequest request) {
        return request.getSeatsWithTypes()
                .stream()
                .filter(seatType -> seatType.id().equals(id))
                .findFirst().map(SeatType::type)
                .orElse(TICKET_TYPE.ADULT);
    }
}
