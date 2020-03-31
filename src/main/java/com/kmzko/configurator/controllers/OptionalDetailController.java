package com.kmzko.configurator.controllers;

import com.kmzko.configurator.domains.conveyor.ConveyorType;
import com.kmzko.configurator.dto.OptionalDetailDto;
import com.kmzko.configurator.mappers.OptionalDetailMapper;
import com.kmzko.configurator.services.KmzkoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/options")
@CrossOrigin(origins = "*")
public class OptionalDetailController {
    private final KmzkoService service;
    private final OptionalDetailMapper mapper;

    public OptionalDetailController(KmzkoService service, OptionalDetailMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<OptionalDetailDto>> getOptions(@RequestParam ConveyorType type) {
        return ResponseEntity.ok(service.getOptions(type).stream().map(mapper::toDto).collect(Collectors.toList()));
    }
}
