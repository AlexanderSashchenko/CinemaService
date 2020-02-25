package com.dev.cinema.service.impl;

import com.dev.cinema.dao.RoleDao;
import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleDao roleDao;
    private final ShoppingCartService shoppingCartService;

    public AuthenticationServiceImpl(UserService userService,
                                     PasswordEncoder passwordEncoder,
                                     RoleDao roleDao,
                                     ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleDao = roleDao;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User user = userService.findByEmail(email);
        if (user.getPassword().equals(passwordEncoder.encode(password))) {
            return user;
        }
        throw new AuthenticationException("Incorrect login or password!");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(roleDao.getByName("USER"));
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
