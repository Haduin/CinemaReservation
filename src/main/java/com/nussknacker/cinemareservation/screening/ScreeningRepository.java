package com.nussknacker.cinemareservation.screening;

import com.nussknacker.cinemareservation.move.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {
    @Query(value = "select m.id,m.title from screening s,movie m where m.id = s.movie_id and  s.start_time between :start and :end",nativeQuery = true)
    List<Movie> selectMoviesByScreeningTime(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end);
}
