package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {

        //add movies to db, get movies from db tests:
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.getAll().forEach(System.out::println);
        Movie movie1 = new Movie();
        movie1.setTitle("Shawshank Redemption");
        movieService.add(movie1);
        Movie movie2 = new Movie();
        movie2.setTitle("Green Mile");
        movieService.add(movie2);
        Movie movie3 = new Movie();
        movie3.setTitle("The Godfather");
        movieService.add(movie3);
        movieService.getAll().forEach(System.out::println);

        //add cinema halls, get all cinema halls:
        CinemaHallService cinemaHallService
                = (CinemaHallService) injector.getInstance(CinemaHallService.class);
        CinemaHall cinemaHall1 = new CinemaHall();
        cinemaHall1.setCapacity(50);
        cinemaHall1.setDescription("Tiny");
        cinemaHallService.add(cinemaHall1);
        CinemaHall cinemaHall2 = new CinemaHall();
        cinemaHall2.setCapacity(100);
        cinemaHall2.setDescription("Medium");
        cinemaHallService.add(cinemaHall2);
        CinemaHall cinemaHall3 = new CinemaHall();
        cinemaHall3.setCapacity(200);
        cinemaHall3.setDescription("Large");
        cinemaHallService.add(cinemaHall3);
        cinemaHallService.getAll().forEach(System.out::println);

        //add movie to movie session, get available sessions list;
        MovieSessionService movieSessionService
                = (MovieSessionService) injector.getInstance(MovieSessionService.class);
        List<MovieSession> movieSessions
                = movieSessionService.findAvailableSessions(3L, LocalDate.now());
        movieSessions.forEach(System.out::println);
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie3);
        movieSession.setCinemaHall(cinemaHall2);
        movieSession.setShowTime(LocalDateTime.now());
        movieSessionService.add(movieSession);
        System.out.println(movieSession);
        movieSessions.forEach(System.out::println);
    }
}
