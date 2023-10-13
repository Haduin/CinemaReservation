package com.nussknacker.cinemareservation.reservation;

import com.nussknacker.cinemareservation.config.StartWithCapital;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ReservationRequest(
        Long screeningId,
        @StartWithCapital
        @NotNull @Size(min = 3)

        String name,
        @StartWithCapital
        @NotNull @Size(min = 3)
        String surname,
        List<Long> seatsId
) {
}
