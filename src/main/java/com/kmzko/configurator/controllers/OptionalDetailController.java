package com.kmzko.configurator.controllers;

import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.dto.conveyor.OptionalDetailDto;
import com.kmzko.configurator.services.KmzkoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/options")
public class OptionalDetailController {
    private final KmzkoService service;

    public OptionalDetailController(KmzkoService service) {
        this.service = service;
    }

    @GetMapping(value = "/{rawType}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OptionalDetailDto>> getOptions(@PathVariable String rawType) {
        ConveyorType type = ConveyorType.safeValueOf(rawType);

        if (type == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(service.getOptions(type));
    }
}
