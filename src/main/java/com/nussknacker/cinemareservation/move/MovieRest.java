package com.nussknacker.cinemareservation.move;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nussknacker.cinemareservation.screening.ScreeningRest;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class MovieRest {
    private Long id;
    private String title;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ScreeningRest> screenings = null;
}
