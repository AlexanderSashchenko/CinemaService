package com.dev.cinema.controller;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.request.OrderRequestDto;
import com.dev.cinema.model.dto.response.OrderResponseDto;
import com.dev.cinema.model.dto.response.TicketResponseDto;
import com.dev.cinema.service.OrderService;
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
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/{userId}/orders")
    public List<OrderResponseDto> getOrderHistory(@PathVariable("userId") Long id) {
        List<Order> ordersList
                = orderService.getOrderHistory(userService.findById(id));
        return ordersList
                .stream()
                .map(this::getOrderDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/{userId}/orders")
    public void complete(@PathVariable("userId") Long id,
                         @RequestBody OrderRequestDto orderRequestDto) {
        orderService.completeOrder(userService.findById(id));
    }

    private OrderResponseDto getOrderDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setUserId(order.getUser().getId());
        orderResponseDto.setOrderDate(order.getOrderDate().toString());
        orderResponseDto.setTicketsList(getTicketDtoList(order));
        return orderResponseDto;
    }

    private List<TicketResponseDto> getTicketDtoList(Order order) {
        return order.getTickets()
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
