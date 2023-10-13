package com.nussknacker.cinemareservation.move;

import com.nussknacker.cinemareservation.screening.ScreeningRest;

import java.util.List;

public record MovieWithScreenings(
        Long id,
        String title,

        List<ScreeningRest> screenings
) {
}
