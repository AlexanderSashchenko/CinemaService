package com.dev.cinema.model.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CinemaHallRequestDto {
    @Min(value = 10, message = "Incorrect cinema hall capacity, minimum value is 10")
    private int capacity;
    @NotEmpty(message = "Entered value must not be empty")
    private String description;
}
