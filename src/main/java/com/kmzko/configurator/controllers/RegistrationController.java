package com.kmzko.configurator.controllers;

import com.kmzko.configurator.dto.UserDto;
import com.kmzko.configurator.exeption.EmailExist;
import com.kmzko.configurator.mappers.UserMapper;
import com.kmzko.configurator.services.detailService.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/join")
@CrossOrigin(origins = "*")
public class RegistrationController {
    private final UserService userService;
    private final UserMapper mapper;

    public RegistrationController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> createUser(@Valid @RequestBody UserDto user) {
        try {
            userService.save(mapper.toEntity(user));
            return ResponseEntity.ok().build();
        }
        catch (EmailExist e) {
            return ResponseEntity.ok(new HashMap<String, String>() {{ put("error", e.getMessage()); }});
        }
    }
}
