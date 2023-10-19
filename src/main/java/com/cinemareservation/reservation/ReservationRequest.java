package com.cinemareservation.reservation;

import com.cinemareservation.seat.SeatType;
import com.cinemareservation.validation.StartWithCapital;
import com.cinemareservation.validation.StartWithCapitalWithDoubleSurname;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class ReservationRequest {
    private Long screeningId;
    @StartWithCapital
    @Size(min = 3)
    private String name;
    @StartWithCapitalWithDoubleSurname
    @Size(min = 3)
    private String surname;
    private List<SeatType> seatsWithTypes;
}
