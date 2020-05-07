package com.kmzko.configurator.security;

import com.kmzko.configurator.entity.user.User;
import com.kmzko.configurator.security.jwt.JwtUserFactory;
import com.kmzko.configurator.services.detailService.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailService implements UserDetailsService {
    private final UserService userService;

    public JwtUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userService.findByUsername(s);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User with username:" + s + "not found");
        }

        return JwtUserFactory.create(user.get());
    }
}
