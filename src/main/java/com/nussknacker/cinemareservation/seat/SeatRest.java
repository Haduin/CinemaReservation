package com.nussknacker.cinemareservation.seat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nussknacker.cinemareservation.room.RoomRest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeatRest {
       private Long id;
       private Long row;
       private Long col;
       @JsonInclude(JsonInclude.Include.NON_NULL)
       private RoomRest room;
       @JsonInclude(JsonInclude.Include.NON_NULL)
       private Boolean taken;
}
