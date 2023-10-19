package com.cinemareservation.screening;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/screening", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ScreeningController {
    private final ScreeningService screeningService;

    @GetMapping
    public ScreeningRest getScreeningDetails(@RequestParam Long screeningId) {
        return screeningService.getDetails(screeningId);
    }

}
