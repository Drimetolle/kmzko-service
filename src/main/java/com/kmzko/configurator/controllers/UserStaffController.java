package com.kmzko.configurator.controllers;

import com.kmzko.configurator.dto.user.BioDto;
import com.kmzko.configurator.dto.user.UserDto;
import com.kmzko.configurator.mappers.UserBioMapper;
import com.kmzko.configurator.services.detailService.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


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
    public ResponseEntity<UserDto> getUser(Authentication authentication) {
        Optional<UserDto> user = userService.findByUsername(authentication.getName());
        return ResponseEntity.ok(user.get());
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BioDto> editBioUser(@Valid @RequestBody BioDto body, Authentication authentication) {
        Optional<BioDto> bio = userService.updateUserInformation(body, authentication.getName());

        return ResponseEntity.ok(bio.get());
    }
}
