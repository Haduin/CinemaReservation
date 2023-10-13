package com.nussknacker.cinemareservation.move;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/movie", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MovieWithScreenings> getAllMoviesWithScreenings(@RequestParam String dateFrom, @RequestParam String dateEnd) {
        return movieService.getAllMoviesWithScreenings(dateFrom, dateEnd);
    }


}
