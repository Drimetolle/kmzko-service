package com.kmzko.service.controllers;

import com.kmzko.service.domains.ConveyorType;
import com.kmzko.service.domains.conveyor.Detail;
import com.kmzko.service.services.KmzkoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DetailController {
    private final KmzkoService service;

    public DetailController(KmzkoService service) {
        this.service = service;
    }

    @GetMapping(value = "/options", produces = "application/json")
    public ResponseEntity<List<Detail>> getOptions(@RequestParam ConveyorType type) {
        return ResponseEntity.ok(service.getOptions(type));
    }
}
