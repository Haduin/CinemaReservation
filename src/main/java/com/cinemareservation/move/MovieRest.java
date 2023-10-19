package com.cinemareservation.move;

import com.cinemareservation.screening.ScreeningRest;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class MovieRest {
    private Long id;
    private String title;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ScreeningRest> screenings;
}
