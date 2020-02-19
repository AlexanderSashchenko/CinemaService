package com.dev.cinema.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSessionResponseDto {
    private long id;
    private long movieId;
    private long cinemaHallId;
    private String showTime;
}
