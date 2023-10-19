package com.cinemareservation.screening;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.cinemareservation.move.MovieRest;
import com.cinemareservation.seat.SeatRest;
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
    private MovieRest movieRest;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SeatRest> seats;
}
