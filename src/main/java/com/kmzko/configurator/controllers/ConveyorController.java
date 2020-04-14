package com.kmzko.configurator.controllers;

import com.kmzko.configurator.domains.conveyor.Conveyor;
import com.kmzko.configurator.domains.conveyor.ConveyorType;
import com.kmzko.configurator.services.detailService.ConveyorDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/conveyors")
public class ConveyorController {
    private final ConveyorDetailService detailService;

    public ConveyorController(ConveyorDetailService detailService) {
        this.detailService = detailService;
    }

    @GetMapping(value = "/template/{rawType}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Conveyor> getConveyorTemplate(@PathVariable String rawType) {
        ConveyorType type = ConveyorType.safeValueOf(rawType);

        if (type == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        Conveyor conveyor = detailService.getConveyorTemplate(type);
        return ResponseEntity.ok(conveyor);
    }
}
