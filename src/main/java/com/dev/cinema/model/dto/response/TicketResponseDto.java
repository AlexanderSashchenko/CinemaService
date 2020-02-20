package com.dev.cinema.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketResponseDto {
    private long id;
    private long movieId;
    private long cinemaHallId;
    private long userId;
    private String showTime;
}
