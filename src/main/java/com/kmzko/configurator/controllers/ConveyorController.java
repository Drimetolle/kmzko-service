package com.kmzko.configurator.controllers;

import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.dto.conveyor.ConveyorDto;
import com.kmzko.configurator.services.detailService.ConveyorDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/conveyors")
public class ConveyorController {
    private final ConveyorDetailService detailService;

    public ConveyorController(ConveyorDetailService detailService) {
        this.detailService = detailService;
    }

    @GetMapping(value = "/template/{rawType}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConveyorDto> getConveyorTemplate(@PathVariable String rawType) {
        ConveyorType type = ConveyorType.safeValueOf(rawType);

        if (type == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        ConveyorDto conveyor = detailService.getConveyorTemplate(type);
        return ResponseEntity.ok(conveyor);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConveyorDto> createNewConveyor(@RequestBody ConveyorDto body) {
        ConveyorDto newBody = detailService.save(body);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBody.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(newBody);
    }
}
