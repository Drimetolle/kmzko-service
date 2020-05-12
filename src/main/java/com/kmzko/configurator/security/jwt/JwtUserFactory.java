package com.kmzko.configurator.security.jwt;

import com.kmzko.configurator.entity.user.Role;
import com.kmzko.configurator.entity.user.AccountStatus;
import com.kmzko.configurator.entity.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getUsername(),
                user.getPassword(),
                user.getStatus().equals(AccountStatus.ACTIVE),
                user.getUpdated(),
                mapGrantedAuthorities(user.getRoles()));
    }

    private static List<GrantedAuthority> mapGrantedAuthorities(Set<Role> userRoles) {
        return userRoles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
