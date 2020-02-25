package com.dev.cinema.security;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);
        UserBuilder userBuilder;
        if (user != null) {
            userBuilder = org.springframework.security.core.userdetails.User.withUsername(email);
            userBuilder.password(user.getPassword());
            userBuilder.roles(getRolesFromSet(user.getRoles()));
        } else {
            throw new UsernameNotFoundException("Failed to find user");
        }
        return userBuilder.build();
    }

    private String[] getRolesFromSet(Set<Role> roles) {
        return roles.stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList())
                .toArray(String[]::new);
    }
}
