package com.dev.cinema.model.dto.request;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketRequestDto {
    @NotEmpty(message = "Entered value must not be empty")
    private long movieId;
    @NotEmpty(message = "Entered value must not be empty")
    private long cinemaHallId;
    @NotEmpty(message = "Entered value must not be empty")
    private long userId;
    @NotEmpty(message = "Entered value must not be empty")
    private String showTime;
}
