package com.nussknacker.cinemareservation.screening;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nussknacker.cinemareservation.move.MovieRest;
import com.nussknacker.cinemareservation.seat.SeatRest;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class ScreeningRest {
    private Long id;
    private LocalDateTime startTime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MovieRest movieRest = null;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SeatRest> seats = null;
}
