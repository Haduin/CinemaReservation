package com.cinemareservation.move;

import com.cinemareservation.screening.ScreeningRest;

import java.util.List;

public record MovieWithScreenings(
        Long id,
        String title,

        List<ScreeningRest> screenings
) {
}
