package com.kmzko.configurator.controllers;

import com.kmzko.configurator.domains.questionnaire.Rate;
import com.kmzko.configurator.domains.conveyor.Conveyor;
import com.kmzko.configurator.dto.ConveyorDto;
import com.kmzko.configurator.mappers.ConveyorMapper;
import com.kmzko.configurator.services.KmzkoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/search")
@CrossOrigin(origins = "*")
public class SearchController {
    private final KmzkoService service;
    private final ConveyorMapper mapper;

    public SearchController(KmzkoService service, ConveyorMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(value = "/conveyors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConveyorDto>> searchByConveyors(@RequestParam Map<String,String> allParams) {
        List<Rate> payload = allParams.keySet().stream()
                .map(i -> new Rate("", allParams.get(i), i)).collect(Collectors.toList());
        List<Conveyor> conveyors = service.getNearConveyors(payload);
        return ResponseEntity.ok(conveyors.stream().map(mapper::toDto).collect(Collectors.toList()));
    }
}
