package com.dev.cinema.controller;

import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.dto.request.CinemaHallRequestDto;
import com.dev.cinema.model.dto.response.CinemaHallResponseDto;
import com.dev.cinema.service.CinemaHallService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private final CinemaHallService cinemaHallService;

    public CinemaHallController(CinemaHallService cinemaHallService) {
        this.cinemaHallService = cinemaHallService;
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAll() {
        List<CinemaHall> cinemaHallsList = cinemaHallService.getAll();
        return cinemaHallsList
                .stream()
                .map(this::getDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void add(@RequestBody CinemaHallRequestDto cinemaHallRequestDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(cinemaHallRequestDto.getCapacity());
        cinemaHall.setDescription(cinemaHallRequestDto.getDescription());
        cinemaHallService.add(cinemaHall);
    }

    private CinemaHallResponseDto getDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto cinemaHallResponseDto = new CinemaHallResponseDto();
        cinemaHallResponseDto.setId(cinemaHall.getId());
        cinemaHallResponseDto.setCapacity(cinemaHall.getCapacity());
        cinemaHallResponseDto.setDescription(cinemaHall.getDescription());
        return cinemaHallResponseDto;
    }
}