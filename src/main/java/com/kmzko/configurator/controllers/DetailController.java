package com.kmzko.configurator.controllers;

import com.kmzko.configurator.domains.conveyor.ConveyorType;
import com.kmzko.configurator.dto.DetailDto;
import com.kmzko.configurator.mappers.DetailMapper;
import com.kmzko.configurator.services.KmzkoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DetailController {
    private final KmzkoService service;
    private final DetailMapper mapper;

    public DetailController(KmzkoService service, DetailMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(value = "/options", produces = "application/json")
    public ResponseEntity<List<DetailDto>> getOptions(@RequestParam ConveyorType type) {
        return ResponseEntity.ok(service.getOptions(type).stream().map(mapper::toDto).collect(Collectors.toList()));
    }
}
