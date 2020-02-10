package com.dev.cinema.service.impl;

import com.dev.cinema.dao.OrderDao;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(User user) {
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        Order order = new Order();
        order.setUser(user);
        order.setTickets(shoppingCart.getTickets());
        order.setOrderDate(LocalDateTime.now());
        orderDao.add(order);
        shoppingCartService.clearShoppingCart(shoppingCart);
        return order;
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getUserOrders(user);
    }
}
