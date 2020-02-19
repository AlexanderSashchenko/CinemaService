package com.dev.cinema.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketRequestDto {
    private long movieId;
    private long cinemaHallId;
    private long userId;
    private String showTime;
}
