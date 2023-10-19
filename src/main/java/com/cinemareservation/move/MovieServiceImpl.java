package com.cinemareservation.move;


import com.cinemareservation.exceptions.InvalidArgumentException;
import com.cinemareservation.screening.ScreeningRest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public List<MovieWithScreenings> getAllMoviesWithScreenings(String startParam, String endParam) {

        try {
            LocalDateTime start = LocalDateTime.parse(startParam, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            LocalDateTime end = LocalDateTime.parse(endParam, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

            if (start.isAfter(end)) {
                throw new InvalidArgumentException("Start date cannot be after end date");
            }


            return movieRepository.findAll()
                    .stream()
                    .map(movie -> mapMovieToMovieWithScreenings(movie, start, end))
                    .filter(movieWithScreenings -> !movieWithScreenings.screenings().isEmpty())
                    .sorted(Comparator.comparing(MovieWithScreenings::title)
                            .thenComparing((movie) -> movie.screenings().stream()
                                    .map(ScreeningRest::getStartTime)
                                    .min(Comparator.naturalOrder())
                                    .orElse(null))
                    )
                    .collect(Collectors.toList());

        } catch (InvalidArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidArgumentException("Date must be format: yyyy-mm-ddTHH:mm:ss like 2023-04-01T12:30:00");
        }
    }

    private MovieWithScreenings mapMovieToMovieWithScreenings(Movie movie, LocalDateTime start, LocalDateTime end) {
        List<ScreeningRest> screeningRests = movie.getScreenings()
                .stream()
                .filter(screening -> start.isBefore(screening.getStartTime()) && end.isAfter(screening.getStartTime()))
                .map(screening -> ScreeningRest.builder().id(screening.getId()).startTime(screening.getStartTime()).build())
                .collect(Collectors.toList());

        return new MovieWithScreenings(
                movie.getId(),
                movie.getTitle(),
                screeningRests
        );
    }

}
