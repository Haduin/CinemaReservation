package com.nussknacker.cinemareservation;

import com.nussknacker.cinemareservation.move.Movie;
import com.nussknacker.cinemareservation.move.MovieRepository;
import com.nussknacker.cinemareservation.reservation.Reservation;
import com.nussknacker.cinemareservation.reservation.ReservationRepository;
import com.nussknacker.cinemareservation.room.Room;
import com.nussknacker.cinemareservation.room.RoomRepository;
import com.nussknacker.cinemareservation.screening.Screening;
import com.nussknacker.cinemareservation.screening.ScreeningRepository;
import com.nussknacker.cinemareservation.seat.Seat;
import com.nussknacker.cinemareservation.seat.SeatRepository;
import com.nussknacker.cinemareservation.seatReservatiion.SeatReservation;
import com.nussknacker.cinemareservation.seatReservatiion.SeatReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class CinemaReservationApplication implements CommandLineRunner {

    private final MovieRepository movieRepository;
    private final ScreeningRepository screeningRepository;
    private final RoomRepository roomRepository;
    private final SeatRepository seatRepository;
    private final SeatReservationRepository seatReservationRepository;
    private final ReservationRepository reservationRepository;

    public static void main(String[] args) {
        SpringApplication.run(CinemaReservationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Room r1 = Room.builder().roomMark("A").build();
        Room r2 = Room.builder().roomMark("B").build();
        Room r3 = Room.builder().roomMark("C").build();

        roomRepository.saveAll(List.of(r1, r2, r3));

        Seat seat1 = Seat.builder().room(r1).col(1L).row(1L).build();
        Seat seat2 = Seat.builder().room(r1).col(1L).row(2L).build();
        Seat seat3 = Seat.builder().room(r1).col(1L).row(3L).build();
        Seat seat4 = Seat.builder().room(r1).col(1L).row(4L).build();
        Seat seat5 = Seat.builder().room(r1).col(1L).row(5L).build();
        Seat seat6 = Seat.builder().room(r1).col(2L).row(1L).build();
        Seat seat7 = Seat.builder().room(r1).col(2L).row(2L).build();
        Seat seat8 = Seat.builder().room(r1).col(2L).row(3L).build();
        Seat seat9 = Seat.builder().room(r1).col(2L).row(4L).build();
        Seat seat10 = Seat.builder().room(r1).col(2L).row(5L).build();

        Seat seat11 = Seat.builder().room(r2).col(1L).row(1L).build();
        Seat seat12 = Seat.builder().room(r2).col(1L).row(2L).build();
        Seat seat13 = Seat.builder().room(r2).col(1L).row(3L).build();
        Seat seat14 = Seat.builder().room(r2).col(1L).row(4L).build();
        Seat seat15 = Seat.builder().room(r2).col(1L).row(5L).build();
        Seat seat16 = Seat.builder().room(r2).col(2L).row(1L).build();
        Seat seat17 = Seat.builder().room(r2).col(2L).row(2L).build();
        Seat seat18 = Seat.builder().room(r2).col(2L).row(3L).build();
        Seat seat19 = Seat.builder().room(r2).col(2L).row(4L).build();
        Seat seat20 = Seat.builder().room(r2).col(2L).row(5L).build();

        Seat seat31 = Seat.builder().room(r3).col(1L).row(1L).build();
        Seat seat32 = Seat.builder().room(r3).col(1L).row(2L).build();
        Seat seat33 = Seat.builder().room(r3).col(1L).row(3L).build();
        Seat seat34 = Seat.builder().room(r3).col(1L).row(4L).build();
        Seat seat35 = Seat.builder().room(r3).col(1L).row(5L).build();
        Seat seat36 = Seat.builder().room(r3).col(2L).row(1L).build();
        Seat seat37 = Seat.builder().room(r3).col(2L).row(2L).build();
        Seat seat38 = Seat.builder().room(r3).col(2L).row(3L).build();
        Seat seat39 = Seat.builder().room(r3).col(2L).row(4L).build();
        Seat seat30 = Seat.builder().room(r3).col(2L).row(5L).build();

        seatRepository.saveAll(List.of(seat1, seat2, seat3, seat4, seat5, seat6, seat7, seat8, seat9, seat10, seat11, seat12, seat13, seat14, seat15, seat16, seat17, seat18, seat19, seat20, seat31, seat32, seat33, seat34, seat35, seat36, seat37, seat38, seat39, seat30));

        Movie m1 = Movie.builder().title("North (Nord)").build();
        Movie m5 = Movie.builder().title("Big Fat Liar").build();
        Movie m2 = Movie.builder().title("Fifty Dead Men Walking").build();
        Movie m3 = Movie.builder().title("Down From the Mountain").build();
        Movie m4 = Movie.builder().title("Wedding, A").build();

        movieRepository.saveAll(List.of(m1, m2, m3, m4, m5));


        Screening s1m1 = Screening.builder().room(r1).movie(m1).startTime(LocalDateTime.now()).build();
        Screening s2m1 = Screening.builder().room(r1).movie(m1).startTime(LocalDateTime.of(2023, Month.APRIL, 1, 16, 15)).build();
        Screening s3m1 = Screening.builder().room(r1).movie(m1).startTime(LocalDateTime.of(2023, Month.APRIL, 1, 20, 15)).build();
        Screening s4m1 = Screening.builder().room(r1).movie(m1).startTime(LocalDateTime.of(2023, Month.APRIL, 2, 10, 0)).build();

        Screening s1m2 = Screening.builder().room(r1).movie(m2).startTime(LocalDateTime.of(2023, Month.FEBRUARY, 2, 12, 15)).build();
        Screening s2m2 = Screening.builder().room(r1).movie(m2).startTime(LocalDateTime.of(2023, Month.FEBRUARY, 2, 16, 15)).build();
        Screening s3m2 = Screening.builder().room(r1).movie(m2).startTime(LocalDateTime.of(2023, Month.FEBRUARY, 2, 20, 15)).build();
        Screening s4m2 = Screening.builder().room(r1).movie(m2).startTime(LocalDateTime.of(2023, Month.FEBRUARY, 2, 10, 0)).build();

        Screening s1m3 = Screening.builder().room(r2).movie(m3).startTime(LocalDateTime.of(2023, Month.JULY, 3, 12, 15)).build();
        Screening s2m3 = Screening.builder().room(r2).movie(m3).startTime(LocalDateTime.of(2023, Month.JULY, 3, 16, 15)).build();
        Screening s3m3 = Screening.builder().room(r2).movie(m3).startTime(LocalDateTime.of(2023, Month.JULY, 3, 20, 15)).build();
        Screening s4m3 = Screening.builder().room(r2).movie(m3).startTime(LocalDateTime.of(2023, Month.JULY, 3, 10, 0)).build();

        Screening s1m4 = Screening.builder().room(r2).movie(m4).startTime(LocalDateTime.of(2023, Month.DECEMBER, 4, 12, 15)).build();
        Screening s2m4 = Screening.builder().room(r2).movie(m4).startTime(LocalDateTime.of(2023, Month.DECEMBER, 4, 16, 15)).build();
        Screening s3m4 = Screening.builder().room(r2).movie(m4).startTime(LocalDateTime.of(2023, Month.DECEMBER, 4, 20, 15)).build();
        Screening s4m4 = Screening.builder().room(r2).movie(m4).startTime(LocalDateTime.of(2023, Month.DECEMBER, 4, 10, 0)).build();

        Screening s1m5 = Screening.builder().room(r1).movie(m5).startTime(LocalDateTime.of(2023, Month.JANUARY, 5, 12, 15)).build();
        Screening s2m5 = Screening.builder().room(r1).movie(m5).startTime(LocalDateTime.of(2023, Month.JANUARY, 5, 16, 15)).build();
        Screening s3m5 = Screening.builder().room(r2).movie(m5).startTime(LocalDateTime.of(2023, Month.JANUARY, 5, 20, 15)).build();
        Screening s4m5 = Screening.builder().room(r2).movie(m5).startTime(LocalDateTime.of(2023, Month.JANUARY, 5, 10, 0)).build();

        screeningRepository.saveAll(List.of(
                s1m1, s2m1, s3m1, s4m1, s2m1,
                s1m2, s2m2, s3m2, s4m2, s2m2,
                s1m3, s2m3, s3m3, s4m3, s2m3,
                s1m4, s2m4, s3m4, s4m4, s2m4,
                s1m5, s2m5, s3m5, s4m5, s2m5
        ));

        Reservation reservation = Reservation.builder().name("KTOS").paid(Boolean.FALSE).surname("KTOSCOS").screening(s1m1).build();
        reservationRepository.save(reservation);
        seatReservationRepository.save(SeatReservation.builder().seat(seat1).screening(s1m1).reservation(reservation).build());

    }
}
