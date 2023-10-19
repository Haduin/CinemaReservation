package com.cinemareservation.move;

import com.cinemareservation.exceptions.InvalidArgumentException;
import com.cinemareservation.screening.Screening;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceImplTest {

    @InjectMocks
    private MovieServiceImpl movieService;

    @Mock
    private MovieRepository movieRepository;

    @Test
    void shouldGetAllMoviesWithScreening() {
        String startParam = "2023-04-01T12:00:00";
        String endParam = "2023-05-01T15:00:00";

        Movie movie1 = new Movie(1L, "Movie 1", Collections.singletonList(Screening.builder().id(1L).startTime(LocalDateTime.parse("2023-04-01T13:00:00")).build()));
        Movie movie2 = new Movie(2L, "Movie 2", Collections.singletonList(Screening.builder().id(2L).startTime(LocalDateTime.parse("2023-04-01T14:00:00")).build()));
        Movie movie3 = new Movie(3L, "Movie 3", Collections.emptyList());

        when(movieRepository.findAll())
                .thenReturn(Arrays.asList(movie1, movie2, movie3));

        List<MovieWithScreenings> result = movieService.getAllMoviesWithScreenings(startParam, endParam);

        assertEquals(2, result.size());
        assertEquals("Movie 1", result.get(0).title());
        assertEquals("Movie 2", result.get(1).title());
    }

    @Test
    void shouldThrowInvalidArgumentExceptionTestOnInvalidDates() {
        String endParam = "2023-04-01T12:00:00";
        String startParam = "2023-05-01T15:00:00";

        when(movieService.getAllMoviesWithScreenings(endParam, startParam)).thenThrow(InvalidArgumentException.class);

        assertThrows(InvalidArgumentException.class, () -> movieService.getAllMoviesWithScreenings(endParam, startParam));
    }

}