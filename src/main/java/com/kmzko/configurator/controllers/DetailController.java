package com.kmzko.configurator.controllers;

import com.kmzko.configurator.dto.conveyor.DetailDto;
import com.kmzko.configurator.services.detailService.ComponentDetailService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/details")
public class DetailController {
    private final ComponentDetailService service;

    public DetailController(ComponentDetailService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DetailDto>> getAllDetails() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DetailDto> deployNewDetail(@Valid @RequestBody DetailDto detail) {
        return ResponseEntity.ok(service.save(detail));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DetailDto> deleteDetail(@PathVariable Long id) {
        if (!service.deleteById(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }
}
