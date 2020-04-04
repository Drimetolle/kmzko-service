package com.kmzko.configurator.controllers;

import com.kmzko.configurator.dto.UserDto;
import com.kmzko.configurator.exeption.EmailExist;
import com.kmzko.configurator.mappers.UserMapper;
import com.kmzko.configurator.services.RegistrationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/join")
@CrossOrigin(origins = "*")
public class RegistrationController {
    private final RegistrationService registrationService;
    private final UserMapper mapper;

    public RegistrationController(RegistrationService registrationService, UserMapper mapper) {
        this.registrationService = registrationService;
        this.mapper = mapper;
    }

    @PostMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> createUser(@Valid @RequestBody UserDto user) {
        try {
            registrationService.createNewUser(mapper.toEntity(user));
            return ResponseEntity.ok().build();
        }
        catch (EmailExist e) {
            return ResponseEntity.ok(new HashMap<String, String>() {{ put("error", e.getMessage()); }});
        }
    }
}
