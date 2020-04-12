package com.kmzko.configurator.controllers;

import com.kmzko.configurator.dto.auth.AuthTokenDto;
import com.kmzko.configurator.dto.auth.AuthenticationRequestDto;
import com.kmzko.configurator.exeption.InvalidRefreshTokenException;
import com.kmzko.configurator.services.AuthorizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthorizationController {
    private final AuthenticationManager authenticationManager;
    private final AuthorizationService authorizationService;

    public AuthorizationController(AuthenticationManager authenticationManager, AuthorizationService authorizationService) {
        this.authenticationManager = authenticationManager;
        this.authorizationService = authorizationService;
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthTokenDto> login(@RequestBody AuthenticationRequestDto bodyAuth) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(bodyAuth.getUsername(), bodyAuth.getPassword()));
            AuthTokenDto tokens = authorizationService.generateTokenPair(bodyAuth.getUsername());
            return ResponseEntity.ok(tokens);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthTokenDto> login(@RequestBody AuthTokenDto bodyAuth) {
        try {
            AuthTokenDto tokens = authorizationService.refreshTokens(bodyAuth.getAccess_token(), bodyAuth.getRefresh_token());
            return ResponseEntity.ok(tokens);
        }
        catch (InvalidRefreshTokenException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
