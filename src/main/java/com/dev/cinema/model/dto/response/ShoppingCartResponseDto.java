package com.dev.cinema.model.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingCartResponseDto {
    private long id;
    private List<TicketResponseDto> ticketsList;
    private long userId;
}
