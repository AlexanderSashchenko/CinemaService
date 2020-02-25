package com.dev.cinema.model.dto.request;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CinemaHallRequestDto {
    @NotEmpty(message = "Entered value must not be empty")
    private int capacity;
    @NotEmpty(message = "Entered value must not be empty")
    private String description;
}
