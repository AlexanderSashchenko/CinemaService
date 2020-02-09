package com.dev.cinema.service;

import java.util.List;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;

public interface OrderService {
    Order completeOrder(User user);

    List<Order> getOrderHistory(User user);
}
