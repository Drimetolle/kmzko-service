package com.kmzko.configurator.services;

import com.kmzko.configurator.dto.auth.AuthTokenDto;
import com.kmzko.configurator.dto.user.UserDto;
import com.kmzko.configurator.entity.Session;
import com.kmzko.configurator.entity.user.User;
import com.kmzko.configurator.exeption.InvalidRefreshTokenException;
import com.kmzko.configurator.repositories.SessionRepo;
import com.kmzko.configurator.repositories.UserRepo;
import com.kmzko.configurator.security.jwt.JwtTokenProvider;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizationService {
    private final JwtTokenProvider jwtTokenProvider;
    private final SessionRepo sessionRepository;
    private final UserRepo userRepo;

    public AuthorizationService(JwtTokenProvider jwtTokenProvider, SessionRepo sessionRepository, UserRepo userRepo) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.sessionRepository = sessionRepository;
        this.userRepo = userRepo;
    }

    public AuthTokenDto generateTokenPair(String username) {
        Optional<User> user = userRepo.findByUsername(username);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        AuthTokenDto token = createTokensFromUser(user.get());
        saveOrUpdateToken(user.get(), token.getRefresh_token());

        return token;
    }

    public AuthTokenDto refreshTokens(String accessToken, String refreshToken) {
        String username = jwtTokenProvider.getUsernameWithoutCheckExpired(accessToken);
        Optional<User> user = userRepo.findByUsername(username);

        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new InvalidRefreshTokenException("Refresh token is expired");
        }

        // contain in db
        Session session = sessionRepository.findByToken(refreshToken);

        if (session == null) {
            throw new InvalidRefreshTokenException("Refresh token is invalid");
        }

        AuthTokenDto token = createTokensFromUser(user.get());
        saveToken(user.get(), token.getRefresh_token());

        return token;
    }

    private AuthTokenDto createTokensFromUser(User user) {
        String accessToken = jwtTokenProvider.createToken(user);
        String refreshToken = jwtTokenProvider.createRefreshToken(user);

        return new AuthTokenDto(accessToken, refreshToken);
    }

    private void saveToken(User user, String refreshToken) {
        Session session = sessionRepository.findByUser(user);
        session.setToken(refreshToken);
        sessionRepository.save(session);
    }

    private void saveOrUpdateToken(User user, String refreshToken) {
        Session session = sessionRepository.findByUser(user);

        if (session == null) {
            sessionRepository.save(new Session(refreshToken, user));
        }
        else {
            session.setToken(refreshToken);
            sessionRepository.save(session);
        }
    }
}
