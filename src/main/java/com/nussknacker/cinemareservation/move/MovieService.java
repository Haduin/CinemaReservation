package com.nussknacker.cinemareservation.move;

import java.util.List;

public interface MovieService {
    List<MovieWithScreenings> getAllMoviesWithScreenings(String from, String end);
}
