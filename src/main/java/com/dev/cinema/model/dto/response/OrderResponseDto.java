package com.dev.cinema.model.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {
    private long id;
    private long userId;
    private List<TicketResponseDto> ticketsList;
    private String orderDate;
}
