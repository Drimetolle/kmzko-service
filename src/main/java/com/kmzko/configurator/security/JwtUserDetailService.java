package com.kmzko.configurator.security;

import com.kmzko.configurator.entity.user.User;
import com.kmzko.configurator.repositories.UserRepo;
import com.kmzko.configurator.security.jwt.JwtUserFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailService implements UserDetailsService {
    private final UserRepo repo;

    public JwtUserDetailService(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = repo.findByUsername(s);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User with username:" + s + "not found");
        }

        return JwtUserFactory.create(user.get());
    }
}
