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

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/shopping-carts")
    public ShoppingCartResponseDto get(Authentication authentication) {
        String email = authentication.getName();
        return getDto(shoppingCartService.getByUser(userService.findByEmail(email)));
    }

    @PostMapping("/shopping-carts")
    public String addMovieSession(Authentication authentication,
                                @RequestBody Long movieSessionId) {
        String email = authentication.getName();
        shoppingCartService.addSession(movieSessionService.findById(movieSessionId),
                userService.findByEmail(email));
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
