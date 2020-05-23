package com.kmzko.configurator.controllers;

import com.kmzko.configurator.dto.conveyor.ConveyorDto;
import com.kmzko.configurator.dto.questionnaire.RateDto;
import com.kmzko.configurator.services.KmzkoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    private final KmzkoService service;

    public SearchController(KmzkoService service) {
        this.service = service;
    }

    @GetMapping(value = "/conveyors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConveyorDto>> searchByConveyors(@RequestParam Map<String, String> allParams) {
        List<RateDto> payload = allParams.keySet().stream()
                .map(i -> new RateDto(null, allParams.get(i), i, null)).collect(Collectors.toList());
        List<ConveyorDto> conveyors = service.getNearConveyors(payload);
        return ResponseEntity.ok(conveyors);
    }
}
