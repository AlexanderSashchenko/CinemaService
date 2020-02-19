package com.dev.cinema.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CinemaHallResponseDto {
    private long id;
    private int capacity;
    private String description;
}
