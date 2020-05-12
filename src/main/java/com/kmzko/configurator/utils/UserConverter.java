package com.kmzko.configurator.utils;

import com.kmzko.configurator.entity.user.User;
import com.kmzko.configurator.security.jwt.JwtUser;
import com.kmzko.configurator.services.detailService.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    private static UserService userService;

    public UserConverter(UserService userService) {
        this.userService = userService;
    }

    public static User convertAuthenticationToUser(Authentication authentication) {
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        String name = jwtUser.getUsername();

        return userService.findByUsername(jwtUser.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username: " + name + "not found"));
    }
}
