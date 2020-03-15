package com.kmzko.service.controllers;

import com.kmzko.service.domains.conveyor.Conveyor;
import com.kmzko.service.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/search")
@CrossOrigin(origins = "*")
public class SearchController {
    @Autowired
    private SearchService service;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Conveyor>> getListOfConveyorType() {
        List<Conveyor> conveyors = service.getNearConveyors();
        return ResponseEntity.ok(conveyors);
    }
}
