package com.cinemareservation.reservation;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/reservation", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponse> makeReservation(@Valid @RequestBody final ReservationRequest request) {
        return new ResponseEntity<>(reservationService.makeReservation(request), HttpStatus.CREATED);
    }

}
