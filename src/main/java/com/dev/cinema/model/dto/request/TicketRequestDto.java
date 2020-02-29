package com.dev.cinema.model.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketRequestDto {
    @Min(value = 1, message = "Incorrect movie id")
    private long movieId;
    @Min(value = 1, message = "Incorrect cinema hall id")
    private long cinemaHallId;
    @Min(value = 1, message = "Incorrect user id")
    private long userId;
    @NotEmpty(message = "Entered value must not be empty")
    private String showTime;
}
