package com.dev.cinema.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSessionRequestDto {
    private long movieId;
    private long cinemaHallId;
    private String showTime;
}


