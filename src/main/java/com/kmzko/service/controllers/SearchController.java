package com.kmzko.service.controllers;

import com.kmzko.service.domains.Rate;
import com.kmzko.service.domains.conveyor.Conveyor;
import com.kmzko.service.services.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/search")
@CrossOrigin(origins = "*")
public class SearchController {
    private final SearchService service;

    public SearchController(SearchService service) {
        this.service = service;
    }

    @GetMapping(value = "/conveyors", produces = "application/json")
    public ResponseEntity<List<Conveyor>> getListOfConveyor(@RequestParam Map<String,String> allParams) {
        List<Rate> payload = allParams.keySet().stream()
                .map(i -> new Rate(i, allParams.get(i))).collect(Collectors.toList());
        List<Conveyor> conveyors = service.getNearConveyors(payload);
        return ResponseEntity.ok(conveyors);
    }

    @GetMapping(value = "/conveyors/{id}", produces = "application/json")
    public ResponseEntity<Conveyor> getConveyorById(@PathVariable long id) {
        Conveyor conveyors = service.getConveyorById(id);
        return ResponseEntity.ok(conveyors);
    }
}
