package com.kmzko.configurator.controllers;

import com.kmzko.configurator.entity.user.User;
import com.kmzko.configurator.security.jwt.JwtUser;
import com.kmzko.configurator.services.detailService.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/session")
public class SessionController {
    private final UserService userService;

    public SessionController(UserService userService) {
        this.userService = userService;
    }

    private User convertAuthenticationToUser(Authentication authentication) {
        JwtUser jwtUser =(JwtUser) authentication.getPrincipal();
        return userService.findByUsername(jwtUser.getUsername()).orElseThrow(() ->
                new UsernameNotFoundException("Username: " + jwtUser.getUsername() + "not found"));
    }
}
