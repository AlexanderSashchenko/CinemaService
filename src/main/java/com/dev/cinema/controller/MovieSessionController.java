package com.dev.cinema.controller;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.request.MovieSessionRequestDto;
import com.dev.cinema.model.dto.response.MovieSessionResponseDto;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final CinemaHallService cinemaHallService;
    private final MovieService movieService;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  CinemaHallService cinemaHallService,
                                  MovieService movieService) {
        this.movieSessionService = movieSessionService;
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
    }

    @GetMapping("/{movieId}/movie-sessions")
    public List<MovieSessionResponseDto> getAll(@PathVariable("movieId") Long id,
                                                @RequestParam String date) {
        List<MovieSession> movieSessionsList = movieSessionService.findAvailableSessions(id,
                LocalDateTime.parse(date).toLocalDate());
        return movieSessionsList
                .stream()
                .map(this::getDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/{movieId}/movie-sessions")
    public String add(@PathVariable("movieId") Long id,
                    @RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movieService
                .findById(id));
        movieSession.setCinemaHall(cinemaHallService
                .findById(movieSessionRequestDto.getCinemaHallId()));
        movieSession.setShowTime(LocalDateTime.parse(movieSessionRequestDto.getShowTime()));
        movieSessionService.add(movieSession);
        return "Added new movie session successfully.";
    }

    private MovieSessionResponseDto getDto(MovieSession movieSession) {
        MovieSessionResponseDto movieSessionResponseDto = new MovieSessionResponseDto();
        movieSessionResponseDto.setId(movieSession.getId());
        movieSessionResponseDto.setMovieId(movieSession.getMovie().getId());
        movieSessionResponseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        movieSessionResponseDto.setShowTime(movieSession.getShowTime().toString());
        return movieSessionResponseDto;
    }
}
