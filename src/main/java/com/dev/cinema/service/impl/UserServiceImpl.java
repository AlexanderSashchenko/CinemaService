package com.dev.cinema.service.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.model.User;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final ShoppingCartService shoppingCartService;
    private final UserDao userDao;

    public UserServiceImpl(ShoppingCartService shoppingCartService,
                           UserDao userDao) {
        this.shoppingCartService = shoppingCartService;
        this.userDao = userDao;
    }

    @Override
    public User add(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userDao.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }
}
