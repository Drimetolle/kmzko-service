package com.kmzko.configurator.controllers;

import com.kmzko.configurator.dto.UserDto;
import com.kmzko.configurator.exeption.EmailExistException;
import com.kmzko.configurator.mappers.UserMapper;
import com.kmzko.configurator.services.detailService.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/join")
public class RegistrationController {
    private final UserService userService;
    private final UserMapper mapper;

    public RegistrationController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> checkFieldForUniqueness(@RequestParam Optional<String> username,
                                                           @RequestParam Optional<String> email) {
        if (username.isPresent()) {
            if (userService.findByUsername(username.get()) != null) {
                return ResponseEntity.ok(new HashMap<String, String>() {{ put("error", "username exist"); put("status", "false"); }});
            }
        }
        if (email.isPresent()) {
            if (userService.findByEmail(email.get()).isPresent()) {
                return ResponseEntity.ok(new HashMap<String, String>() {{ put("error", "email exist"); put("status", "false"); }});
            }
        }
        return ResponseEntity.ok(new HashMap<String, String>() {{ put("status", "true"); }});
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> createUser(@Valid @RequestBody UserDto user) {
        try {
            Map<String, String> response = new HashMap<>();
            userService.save(mapper.toEntity(user));

            response.put("status", "ok");

            return ResponseEntity.ok(response);
        }
        catch (EmailExistException e) {
            return ResponseEntity.ok(new HashMap<String, String>() {{ put("error", e.getMessage()); }});
        }
    }
}
