package com.cinemareservation.room;

import com.cinemareservation.screening.ScreeningRest;
import com.cinemareservation.seat.SeatRest;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public record RoomRest(
        Long id,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        List<ScreeningRest> screenings,
        String roomMark,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        List<SeatRest> seats
) {
}
