package com.dev.cinema.model.dto.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
    private long userId;
    private List<TicketRequestDto> ticketsList;
    private String orderDate;
}
