package com.kmzko.configurator.controllers;

import com.kmzko.configurator.dto.BioDto;
import com.kmzko.configurator.entity.user.User;
import com.kmzko.configurator.mappers.UserBioMapper;
import com.kmzko.configurator.security.jwt.JwtUser;
import com.kmzko.configurator.services.detailService.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserStaffController {
    private final UserBioMapper userBioMapper;
    private final UserService userService;

    public UserStaffController(UserBioMapper userBioMapper,
                               UserService userService) {
        this.userBioMapper = userBioMapper;
        this.userService = userService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BioDto> getUser(Authentication authentication) {
        User user = convertAuthenticationToUser(authentication);
        return ResponseEntity.ok(userBioMapper.toDto(user));
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BioDto> editBioUser(@Valid @RequestBody BioDto body, Authentication authentication) {
        User user = convertAuthenticationToUser(authentication);

        user = userService.findByUsername(user.getUsername()).get();

        user.setName(body.getName());
        user.setEmail(body.getEmail());

        return ResponseEntity.ok(userBioMapper.toDto(user));
    }

    private User convertAuthenticationToUser(Authentication authentication) {
        JwtUser jwtUser =(JwtUser) authentication.getPrincipal();
        String name = jwtUser.getUsername();

        return userService.findByUsername(jwtUser.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username: " + name + "not found"));
    }
}
