package com.nussknacker.cinemareservation.room;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nussknacker.cinemareservation.screening.ScreeningRest;
import com.nussknacker.cinemareservation.seat.SeatRest;

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
