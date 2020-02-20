package com.dev.cinema.controller;

import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.response.ShoppingCartResponseDto;
import com.dev.cinema.model.dto.response.TicketResponseDto;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final MovieSessionService movieSessionService;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  UserService userService,
                                  MovieSessionService movieSessionService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.movieSessionService = movieSessionService;
    }

    @GetMapping("/{userId}/shopping-carts")
    public ShoppingCartResponseDto get(@PathVariable("userId") Long id) {
        return getDto(shoppingCartService.getByUser(userService.findById(id)));
    }

    @PostMapping("/{userId}/shopping-carts")
    public String addMovieSession(@PathVariable("userId") Long id,
                                @RequestBody Long movieSessionId) {
        shoppingCartService.addSession(movieSessionService.findById(movieSessionId),
                userService.findById(id));
        return "Added new movie session to shopping cart";
    }

    private ShoppingCartResponseDto getDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setId(shoppingCart.getId());
        shoppingCartResponseDto.setUserId(shoppingCart.getUser().getId());
        shoppingCartResponseDto.setTicketsList(getTicketDtoList(shoppingCart));
        return shoppingCartResponseDto;
    }

    private List<TicketResponseDto> getTicketDtoList(ShoppingCart shoppingCart) {
        return shoppingCart.getTickets()
                .stream()
                .map(this::getTicketDto)
                .collect(Collectors.toList());
    }

    private TicketResponseDto getTicketDto(Ticket ticket) {
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        ticketResponseDto.setId(ticket.getId());
        ticketResponseDto.setCinemaHallId(ticket.getCinemaHall().getId());
        ticketResponseDto.setMovieId(ticket.getMovie().getId());
        ticketResponseDto.setShowTime(ticket.getShowTime().toString());
        ticketResponseDto.setUserId(ticket.getUser().getId());
        return ticketResponseDto;
    }
}
