package com.kmzko.configurator.controllers;

import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.dto.conveyor.OptionalDetailDto;
import com.kmzko.configurator.services.KmzkoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/options")
public class OptionalDetailController {
    private final KmzkoService service;

    public OptionalDetailController(KmzkoService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OptionalDetailDto>> getOptions(@RequestParam(name = "type") Optional<String> rawType) {
        ConveyorType type;
        if (rawType.isPresent()) {
            type = ConveyorType.safeValueOf(rawType.get());
            return ResponseEntity.ok(service.getOptions(type));
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }
}
